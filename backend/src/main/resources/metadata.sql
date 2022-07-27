INSERT INTO USER (USER_EMAIL, USER_ID, USER_IMG, USER_NAME, USER_PW, USER_TYPE) VALUES ('qkrrldbs@naver.com', 'qkrrldbs', NULL, '박기윤', '$2a$10$YnkA5MP1t8xCgbuq1/HjYecxIjMOrbWyYJGfyTM9Ao.WefSuRo8te', 'USER');
INSERT INTO USER (USER_EMAIL, USER_ID, USER_IMG, USER_NAME, USER_PW, USER_TYPE) VALUES ('dbstjrcks@naver.com', 'dbstjrcks', NULL, '윤석찬', '$2a$10$hXB7xotQReXhZ3yPOMS8l.URHhnuy726B0wVABKBUCMeSyGJZRaqW', 'USER');
INSERT INTO USER (USER_EMAIL, USER_ID, USER_IMG, USER_NAME, USER_PW, USER_TYPE) VALUES ('dlwlsgod@naver.com', 'dlwlsgod', NULL, '이진행', '$2a$10$syYEnMGmcfPHpP6Elu./X.vlO756Benk91UHh3yKB4zIg.XiKSK5C', 'USER');
INSERT INTO USER (USER_EMAIL, USER_ID, USER_IMG, USER_NAME, USER_PW, USER_TYPE) VALUES ('tjdanswh@naver.com', 'tjdanswh', NULL, '성문조', '$2a$10$xSLdP./C0wM0YHZSvk90zuUKWTgkc4qUDNz..gpbnwgSBVTokDxna', 'USER');
INSERT INTO USER (USER_EMAIL, USER_ID, USER_IMG, USER_NAME, USER_PW, USER_TYPE) VALUES ('rkdwkdgh@naver.com', 'rkdwkdgh', NULL, '강장호', '$2a$10$Vm8OR6DgHO0QiwQhDR4dne0MYml1sWXvhmM90zDKL7jdjndi.3j4W', 'USER');

INSERT INTO RESUME (RESUME_TITLE, USER_NO) VALUES ('삼성전자하반기 자기소개서',1);
INSERT INTO RESUME (RESUME_TITLE, USER_NO) VALUES ('현대오토에버 자기소개서',2);
INSERT INTO RESUME (RESUME_TITLE, USER_NO) VALUES ('신한은행 자기소개서',3);
INSERT INTO RESUME (RESUME_TITLE, USER_NO) VALUES ('국민은행 자소서',4);
INSERT INTO RESUME (RESUME_TITLE, USER_NO) VALUES ('LGCNS 자소서',5);

INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 배고픈 유년시절을 보냈..','본인의 성장과정을 기술하세요',1,1);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 어렸을때부터 전자기기에 관심이..','전공선택 이유와 적성에 대해 서술하세요.',2,1);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 삼성전자에 입사해서 이건희 회장님 같은..','본인의 입사 후 포부를 말씀해주세요.',3,1);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 어느 한구석 모난곳 없이..','본인의 성격의 장단점을 서술해주세요.',1,2);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저희 아버지, 어머니 모두 현대 자동차를..','우리 회사에 지원한 동기를 서술해주세요.',2,2);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 저희 아부지를 존경합니다. 이유는..','본인이 존경하는 인물에대해 서술해주세요.',3,2);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('제가 싸피에서 해피하우스라는걸 했는데요..','프로젝트 경험에 대해 서술해주세요.',1,3);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 고객들이 사용하는 서비스를 개발하는 부서에..','희망 부서와 직급에 대해 서술해주세요.',2,3);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('학부생 시절 연구실에서 도시락을 먹었는데..','본인의 가장 열정을 바쳐서 일했던 경험',1,4);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('저는 10년후 여우같은 아내와 토끼같은 자식을...','10년후 본인의 모습을 서술하세요.',2,4);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('친구에게 맛있는 저녁을 사줬던..','다른 사람을 감동시켰던 경험이 있나요',1,5);
INSERT INTO RESUME_DETAIL (ANSWER, ITEM, ITEM_NO, RESUME_NO) VALUES ('이전에 회사에서 월급을 안줘서..','이직/전직 사유가 있나요.',2,5);

INSERT INTO NOTICE (NOTICE_CONTENTS, NOTICE_TITLE, WRITE_DATE) VALUES ('스터디 홈페이지에 오신걸 환영합니다.','저희 스터디 홈페이지에서 좋은시간 보내세요.','2022-07-25');
INSERT INTO NOTICE (NOTICE_CONTENTS, NOTICE_TITLE, WRITE_DATE) VALUES ('[기타] 개인정보처리방침 변경 안내','개인정보처리방침이 변경되었습니다.','2022-07-25');
INSERT INTO NOTICE (NOTICE_CONTENTS, NOTICE_TITLE, WRITE_DATE) VALUES ('7월 업데이트 내역','7월 업데이트 내역입니다.','2022-07-25');
INSERT INTO NOTICE (NOTICE_CONTENTS, NOTICE_TITLE, WRITE_DATE) VALUES ('[긴급] 긴급 점검 시간 안내','긴급 점검 시간을 안내드립니다.','2022-07-25');
INSERT INTO NOTICE (NOTICE_CONTENTS, NOTICE_TITLE, WRITE_DATE) VALUES ('이달의 스터디 선정 안내','7월달 이달의 스터디 선정 결과 안내입니다.','2022-07-25');

INSERT INTO REVIEW (REVIEW_CONTENTS, REVIEW_DATE, REVIEW_TITLE, REVIEW_TYPE, USER_NO) VALUES ('다 예상했던 질문들이었고 준비했던 답을 했다. 다만..', '2022-07-25', '신한은행 면접 회고', 'REAL', '4');
INSERT INTO REVIEW (REVIEW_CONTENTS, REVIEW_DATE, REVIEW_TITLE, REVIEW_TYPE, USER_NO) VALUES ('나의 필살기를 더 어필할 수 있는 답변을 짜야겠다.', '2022-07-20', '신한은행 모의면접 회고', 'FAKE', '4');
INSERT INTO REVIEW (REVIEW_CONTENTS, REVIEW_DATE, REVIEW_TITLE, REVIEW_TYPE, USER_NO) VALUES ('지원동기를 다시 다듬어야겠다.', '2022-07-18', '현대자동차 모의면접 회고', 'FAKE', '1');
INSERT INTO REVIEW (REVIEW_CONTENTS, REVIEW_DATE, REVIEW_TITLE, REVIEW_TYPE, USER_NO) VALUES ('다대다 면접으로 진행되었고...', '2022-07-26', '현대자동차 면접 회고', 'REAL', '1');
