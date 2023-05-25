drop database green_plate_db;
select @@global.transaction_isolation, @@transaction_isolation;
set @@transaction_isolation="read-committed";

create database green_plate_db;
use green_plate_db;

create table t_user(
    id varchar(100) primary key,
    name varchar(100) not null,
    pass varchar(100) not null,
    stamps integer default 0
);

create table t_product(
    id integer auto_increment primary key,
    name varchar(100) not null,
    english_name varchar(100) default "",
    type varchar(20) not null,
    discription varchar(100) default "",
    price integer not null,
    img varchar(100)
);

create table t_store(
	id integer auto_increment primary key,
    name varchar(100) not null,
    latitude long,
    longitude long,
    phone_num varchar(100) not null
);


create table t_order(
    o_id integer auto_increment primary key,
    user_id varchar(100) not null,
    order_table varchar(20),
    order_time timestamp default CURRENT_TIMESTAMP,    
    completed char(1) default 'N',
    store_name varchar(100) not null default '구미 싸피점',
    discount_amount integer default 0,
    pay_type varchar(20) default '신용카드', 
    total_order_price integer default 0,
    constraint fk_order_user foreign key (user_id) references t_user(id) on delete cascade
);

create table t_order_detail(
    d_id integer auto_increment primary key,
    order_id integer not null,
    product_id integer not null,
    dressing_id integer not null,
    added_stuff varchar(100),
    added_quantity varchar(100),
    quantity integer not null default 1,
    constraint fk_order_detail_product foreign key (product_id) references t_product(id) on delete cascade,
    constraint fk_order_detail_order foreign key(order_id) references t_order(o_id) on delete cascade
);                                                 

create table t_stamp(
    id integer auto_increment primary key,
    user_id varchar(100) not null,
    order_id integer not null,
    quantity integer not null default 1,
    constraint fk_stamp_user foreign key (user_id) references t_user(id) on delete cascade,
    constraint fk_stamp_order foreign key (order_id) references t_order(o_id) on delete cascade
);

create table t_coupon(
    id integer auto_increment primary key,
    user_id varchar(100) not null,
    type varchar(100) not null,
    discount_amount integer not null,
    constraint fk_comment_user foreign key(user_id) references t_user(id) on delete cascade
);

select * from t_user;

INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 01', 'name 01', 'pass 01', 4);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 02', 'name 02', 'pass 02', 1);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 03', 'name 03', 'pass 03', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 04', 'name 04', 'pass 04', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 05', 'name 05', 'pass 05', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 06', 'name 06', 'pass 06', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 07', 'name 07', 'pass 07', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 08', 'name 08', 'pass 08', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 09', 'name 09', 'pass 09', 0);
INSERT INTO t_user (id, name, pass, stamps) VALUES ('id 10', 'name 10', 'pass 10', 0);

-- salad
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('로스트 닭다리살 샐러드', 'Roast Chicken Thigh Salad', 'salad', 
	'담백한 닭다리살, 달콤한 단호박, 상큼한 토마토, 매콤한 할라피뇨의 다채로운 맛 (추천 드레싱: 칠리)', 9500, 'salad01.png');
        
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('연어 샐러드', 'Salmon Salad', 'salad', 
	'훈제 연어, 양파, 스윗포테이토의 달콤고소한 조합 (추천 드레싱: 레몬)', 10000, 'salad02.png');

INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('우삼겹 메밀면 샐러드', 'Beef Loin Buckwheat Noodles Salad', 'salad', 
	'육즙가득 우삼겹, 상큼한 오이, 양파, 견과류, 탱글한 메밀면의 완벽 밸런스 (추천 드레싱: 오리엔탈)', 9000, 'salad03.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('단호박 두부 샐러드', 'Sweet Pumpkin Tofu Salad', 'salad', 
	'단백하고 고소한 두부와 달콤한 단호박, 아삭한 당근라페가 어우러진 비건 탄단지 메뉴 (추천 드레싱: 발사믹)', 9000, 'salad04.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('콥 샐러드', 'Cobb Salad', 'salad', 
	'계란, 옥수수, 양파, 올리브, 베이컨 등 다채로운 재료에 시저의 고소함까지! (추천 드레싱: 시저)', 7500, 'salad05.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('탄단지 샐러드', 'Tandanji Salad', 'salad', 
	'단백하고 고소한 두부와 달콤한 단호박, 아삭한 당근라페가 어우러진 비건 탄단지 메뉴 (추천 드레싱: 발사믹)', 8000, 'salad06.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('시저 치킨 샐러드', 'Caesar Chicken Salad', 'salad', 
	'담백한 닭가슴살과 크랜베리, 시저의 담백하고 클래식 한 맛! (추천 드레싱: 시저)', 9000, 'salad07.png');
    
    
-- yogurt
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('플레인 그릭요거트', 'Plain Greek Yogurt', 'yogurt', 
	'1등급 원유의 깊은 풍미가 들어간 꾸덕한 그릭요거트', 3500, 'yogurt_plain.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('블루베리 그릭요거트', 'Bluberry Greek Yogurt', 'yogurt', 
	'슈퍼푸드의 대표주자 블루베리를 가득 담은 그릭요거트', 5000, 'yogurt_blueberry.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('초코 그릭요거트', 'Chocolate Greek Yogurt', 'yogurt', 
	'리얼 초코의 달달함이 가득한 그릭요거트', 5000, 'yogurt_chocolate.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('스페셜 허니 브라우니 그릭요거트', 'Special Brownie Greek Yogurt', 'yogurt', 
	'벌꿀집, 브라우니, 쿠키크럼, 바나나가 들어간 스페셜 그릭요거트', 9500, 'yogurt_special_brownie.png');
    
INSERT INTO t_product (name, english_name, type, discription, price, img)
	VALUES ('스페셜 허니 브라우니 그릭요거트', 'Special Honey Greek Yogurt', 'yogurt', 
	'벌꿀집, 그래놀라, 바나나가 들어간 스페셜한 그릭요거트', 8000, 'yogurt_special_honey.png');
    
-- salad topping

INSERT INTO t_product (name, type, price, img) VALUES ('연어', 'salad_topping', 4000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('우삼겹', 'salad_topping', 3000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('베이컨', 'salad_topping', 2000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('닭가슴살', 'salad_topping', 1500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('두부', 'salad_topping', 1500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('단호박', 'salad_topping', 1500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('토마토', 'salad_topping', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('계란', 'salad_topping', 500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('옥수수', 'salad_topping', 500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('양파', 'salad_topping', 500, 'hide.png');

-- greek yogurt topping
INSERT INTO t_product (name, type, price, img) VALUES ('딸기', 'yogurt_topping', 3000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('키위', 'yogurt_topping', 3000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('블루베리', 'yogurt_topping', 2500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('바나나', 'yogurt_topping', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('벌꿀집', 'yogurt_topping', 3000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('브라우니', 'yogurt_topping', 2500, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('그래놀라', 'yogurt_topping', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('쿠키크럼', 'yogurt_topping', 1000, 'hide.png');

-- salad dressing
INSERT INTO t_product (name, type, price, img) VALUES ('시저 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('오리엔탈 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('발사믹 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('레몬 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('머스타드 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('칠리 드레싱', 'salad_dressing', 1000, 'hide.png');
INSERT INTO t_product (name, type, price, img) VALUES ('X', 'salad_dressing', 1000, 'hide.png');

-- greek yogurt dressing
INSERT INTO t_product (name, type, price, img) VALUES ('X', 'yogurt_dressing', 0, 'hide.png');


INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 01', 'order_table 01', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 02', 'order_table 02', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 03', 'order_table 03', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 04', 'order_table 04', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 05', 'order_table 05', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 06', 'order_table 06', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 07', 'order_table 07', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 08', 'order_table 08', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 09', 'order_table 09', '구미 싸피점', 0, '신용카드');
INSERT INTO t_order (user_id, order_table, store_name, discount_amount, pay_type) VALUES ('id 10', 'order_table 10', '구미 싸피점', 0, '신용카드');


INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (1, 1, 31, "연어,닭가슴살,계란", "1,1,1", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (1, 2, 32, "단호박,계란", "1,1", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (2, 3, 33, "두부", "1", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (3, 3, 32, "베이컨,옥수수", "1,2", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (4, 6, 33, "토마토", "1", 2);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (5, 5, 35, "토마토,양파", "2,1", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (6, 4, 32, "양파", "1", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (7, 4, 36, "단호박,두부", "1,1", 3);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (8, 3, 34, "옥수수,양파", "3,1", 2);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (9, 7, 31, "두부,단호박", "3,2", 1);
INSERT INTO t_order_detail (order_id, product_id, dressing_id, added_stuff, added_quantity, quantity) VALUES (10, 1, 34, "계란,옥수수", "3,3", 2);


INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 01', 1, 4);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 02', 2, 1);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 03', 3, 3);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 04', 4, 4);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 05', 5, 5);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 06', 6, 6);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 07', 7, 7);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 08', 8, 8);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 09', 9, 9);
INSERT INTO t_stamp (user_id, order_id, quantity) VALUES ('id 10', 10, 10);

INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 01', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 01', '등급 업그레이드', 5000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 02', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 03', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 04', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 05', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 06', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 07', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 08', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 09', '신규 가입', 7000);
INSERT INTO t_coupon(user_id, type, discount_amount) VALUES ('id 10', '신규 가입', 7000);


INSERT INTO t_store(name, latitude, longitude, phone_num) VALUES ('구미점', 36.128156, 128.332765, '01012345678');
INSERT INTO t_store(name, latitude, longitude, phone_num) VALUES ('송정점', 36.116350, 128.350211, '01087654321');
INSERT INTO t_store(name, latitude, longitude, phone_num) VALUES ('인동점', 36.107370, 128.49145, '01012225678');
INSERT INTO t_store(name, latitude, longitude, phone_num) VALUES ('구미공단점', 36.101625, 128.386003, '01012345677');
INSERT INTO t_store(name, latitude, longitude, phone_num) VALUES ('진평점', 36.0969960, 128.427679, '01032145678');



select * from t_order;

commit;