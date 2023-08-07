# webcrawler
프로젝트에 사용된 코드는 
webcrawler/src/main/java/com/test2/webcrawler/crwaling/service/WebCrawling.java
에서 확인 해볼 수 있습니다.

각 사이트,뉴스타입마다 크롤링해오는 테스트 코드는 test에서 확인 할 수 있습니다.

java 1.8버전을 사용해주세요
KOMORAN이 자바 1.8버전에서만 동작합니다.

개인정보 때문에 application.properties,yml 파일이 존재 하지 않습니다 설정하시고 사용해주세요

gpt를 사용하기 위해서는 API키가 필요로 합니다.
처음 사용하시는 분이라면 아래 유튜브 링크를 참조해주세요

https://www.youtube.com/watch?v=HlDkuFy8xRM&t=1201s



<h1>DB</h1>
create table a_morph
(
    sub      varchar(50) null,
    category varchar(50) null
)
    collate = utf8mb4_unicode_ci;
<br>
create table e_cloud
(
    create_at datetime    not null,
    content1  varchar(40) null,
    content2  varchar(40) null,
    content3  varchar(40) null,
    content4  varchar(40) null,
    content5  varchar(40) null,
    content6  varchar(40) null,
    content7  varchar(40) null,
    content8  varchar(40) null,
    content9  varchar(40) null,
    content10 varchar(40) null,
    id        int auto_increment
        primary key
)
    collate = utf8mb4_unicode_ci;
<br>    

create table economy
(
    e_id        int auto_increment
        primary key,
    news_title  text     null,
    search_time datetime null
)
    collate = utf8mb4_unicode_ci;
<br>

create table i_cloud
(
    create_at datetime    not null,
    content1  varchar(40) null,
    content2  varchar(40) null,
    content3  varchar(40) null,
    content4  varchar(40) null,
    content5  varchar(40) null,
    content6  varchar(40) null,
    content7  varchar(40) null,
    content8  varchar(40) null,
    content9  varchar(40) null,
    content10 varchar(40) null,
    id        int auto_increment
        primary key
)
    collate = utf8mb4_unicode_ci;
<br>
create table it
(
    i_id        int auto_increment
        primary key,
    news_title  text     null,
    search_time datetime null
)
    collate = utf8mb4_unicode_ci;
<br>
create table p_cloud
(
    create_at datetime    not null,
    content1  varchar(40) null,
    content2  varchar(40) null,
    content3  varchar(40) null,
    content4  varchar(40) null,
    content5  varchar(40) null,
    content6  varchar(40) null,
    content7  varchar(40) null,
    content8  varchar(40) null,
    content9  varchar(40) null,
    content10 varchar(40) null,
    id        int auto_increment
        primary key
)
    collate = utf8mb4_unicode_ci;
<br>
create table politics
(
    p_id        int auto_increment
        primary key,
    news_title  text     null,
    search_time datetime null
)
    collate = utf8mb4_unicode_ci;
<br>
create table request_and_response
(
    id        int unsigned auto_increment
        primary key,
    news_type varchar(20) null,
    request   text        null,
    response  text        null,
    create_at datetime    null,
    cloud_id  int         not null
)
    collate = utf8mb4_unicode_ci;
<br>
create table s_cloud
(
    create_at datetime    not null,
    content1  varchar(40) null,
    content2  varchar(40) null,
    content3  varchar(40) null,
    content4  varchar(40) null,
    content5  varchar(40) null,
    content6  varchar(40) null,
    content7  varchar(40) null,
    content8  varchar(40) null,
    content9  varchar(40) null,
    content10 varchar(40) null,
    id        int auto_increment
        primary key
)
    collate = utf8mb4_unicode_ci;
<br>
create table society
(
    s_id        int auto_increment
        primary key,
    news_title  text     null,
    search_time datetime null
)
    collate = utf8mb4_unicode_ci;
<br>
