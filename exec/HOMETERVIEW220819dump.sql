-- MySQL dump 10.13  Distrib 8.0.28, for macos11 (x86_64)
--
-- Host: i7b105.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply` (
  `apply_no` bigint NOT NULL AUTO_INCREMENT,
  `apply_type` varchar(255) DEFAULT NULL,
  `recruit_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`apply_no`),
  KEY `FKrbiux2nh92p5up4507ftqn1kq` (`recruit_no`),
  KEY `FKd1jlr2h785iqikfh5axc5wnwf` (`user_no`),
  CONSTRAINT `FKd1jlr2h785iqikfh5axc5wnwf` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `FKrbiux2nh92p5up4507ftqn1kq` FOREIGN KEY (`recruit_no`) REFERENCES `recruit` (`recruit_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
INSERT INTO `apply` VALUES (1,'LEADER',2,2),(2,'LEADER',3,3),(3,'LEADER',4,4),(4,'LEADER',5,5),(5,'LEADER',6,4);
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_question`
--

DROP TABLE IF EXISTS `common_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `common_question` (
  `question_no` bigint NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) DEFAULT NULL,
  `question_type` varchar(255) DEFAULT NULL,
  `writer_no` bigint DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  PRIMARY KEY (`question_no`),
  KEY `FK9v6kgw19v6x6q436paw0s9wlq` (`std_no`),
  CONSTRAINT `FK9v6kgw19v6x6q436paw0s9wlq` FOREIGN KEY (`std_no`) REFERENCES `study` (`std_no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_question`
--

LOCK TABLES `common_question` WRITE;
/*!40000 ALTER TABLE `common_question` DISABLE KEYS */;
INSERT INTO `common_question` VALUES (1,'자신의 학점에 대해 어떻게 생각하시나요?','FREE',1,1),(2,'자신을 채용해야 하는 이유는 무엇인가요?','FREE',2,1),(3,'자신만의 스트레스 해소법을 말해주세요.','FREE',3,1),(4,'희망하지 않는 분야에 배치된다면 어떻게 하실건가요?','FREE',4,1),(5,'불합리한 대우를 받는다면 어떻게 대처하실건가요?','FREE',5,1),(6,'네트워크에서 프로세스간 통신 방식에 대해 말해보세요.','JOB',1,1),(7,'프로세스와 쓰레드의 차이에 대해서 설명해주세요.','JOB',2,1),(8,'알고있는 정렬 알고리즘에 대해 설명해주세요.','JOB',3,1),(9,'스택과 큐의 차이점에 대해 설명해주세요.','JOB',4,1),(10,'JVM에 동작방식에 대해 설명해주세요.','JOB',5,1);
/*!40000 ALTER TABLE `common_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `d_day`
--

DROP TABLE IF EXISTS `d_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `d_day` (
  `dday_no` bigint NOT NULL AUTO_INCREMENT,
  `dday_date` varchar(255) DEFAULT NULL,
  `dday_title` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`dday_no`),
  KEY `FKt2u07d7slqe0676whdgcw6na` (`user_no`),
  CONSTRAINT `FKt2u07d7slqe0676whdgcw6na` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_day`
--

LOCK TABLES `d_day` WRITE;
/*!40000 ALTER TABLE `d_day` DISABLE KEYS */;
INSERT INTO `d_day` VALUES (1,'2022-09-02','삼성전자 면접',1),(2,'2022-09-05','국민은행 서류 발표',1),(3,'2022-09-11','현대 코딩테스트',1),(4,'2022-09-02','삼성전자 면접',2),(5,'2022-09-06','현대자동차 면접',2),(6,'2022-09-09','LG CNS 면접',2),(7,'2022-09-02','삼성전자 면접',3),(8,'2022-08-28','당근마켓 코딩테스트',3),(9,'2022-09-16','롯데정보통신 면접',3),(10,'2022-09-02','삼성전자 면접',4),(11,'2022-09-11','현대 코딩테스트',4),(12,'2022-09-9','LG CNS 면접',4),(13,'2022-09-02','삼성전자 면접',5),(14,'2022-09-06','현대자동차 면접',5),(15,'2022-09-16','롯데정보통신 면접',5);
/*!40000 ALTER TABLE `d_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_no` bigint NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  `write_date` varchar(255) DEFAULT NULL,
  `writer_no` bigint DEFAULT NULL,
  PRIMARY KEY (`feedback_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_no` bigint NOT NULL AUTO_INCREMENT,
  `notice_contents` varchar(255) DEFAULT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  `write_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notice_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'스터디 홈페이지에 오신걸 환영합니다.','저희 스터디 홈페이지에서 좋은시간 보내세요.','2022-07-25'),(2,'[기타] 개인정보처리방침 변경 안내','개인정보처리방침이 변경되었습니다.','2022-07-25'),(3,'7월 업데이트 내역','7월 업데이트 내역입니다.','2022-07-25'),(4,'[긴급] 긴급 점검 시간 안내','긴급 점검 시간을 안내드립니다.','2022-07-25'),(5,'이달의 스터디 선정 안내','7월달 이달의 스터디 선정 결과 안내입니다.','2022-07-25');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_question`
--

DROP TABLE IF EXISTS `personal_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_question` (
  `question_no` bigint NOT NULL AUTO_INCREMENT,
  `contents` varchar(255) DEFAULT NULL,
  `saved` varchar(255) DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  `writer_no` bigint DEFAULT NULL,
  `detail_no` bigint DEFAULT NULL,
  PRIMARY KEY (`question_no`),
  KEY `FKanbhqniokosqxab0rihjbsd3h` (`detail_no`),
  CONSTRAINT `FKanbhqniokosqxab0rihjbsd3h` FOREIGN KEY (`detail_no`) REFERENCES `resume_detail` (`detail_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_question`
--

LOCK TABLES `personal_question` WRITE;
/*!40000 ALTER TABLE `personal_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recording`
--

DROP TABLE IF EXISTS `recording`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recording` (
  `record_no` bigint NOT NULL AUTO_INCREMENT,
  `save_date` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  PRIMARY KEY (`record_no`),
  KEY `FKpa7j292r05okgagtgdsmrx39m` (`std_no`),
  CONSTRAINT `FKpa7j292r05okgagtgdsmrx39m` FOREIGN KEY (`std_no`) REFERENCES `study` (`std_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recording`
--

LOCK TABLES `recording` WRITE;
/*!40000 ALTER TABLE `recording` DISABLE KEYS */;
/*!40000 ALTER TABLE `recording` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruit`
--

DROP TABLE IF EXISTS `recruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruit` (
  `recruit_no` bigint NOT NULL AUTO_INCREMENT,
  `com_name` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `recruit_status` varchar(255) DEFAULT NULL,
  `recruit_title` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `std_day` varchar(255) DEFAULT NULL,
  `std_detail` varchar(255) DEFAULT NULL,
  `std_img` varchar(255) DEFAULT NULL,
  `std_limit` int DEFAULT NULL,
  `std_name` varchar(255) DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  `std_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`recruit_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruit`
--

LOCK TABLES `recruit` WRITE;
/*!40000 ALTER TABLE `recruit` DISABLE KEYS */;
INSERT INTO `recruit` VALUES (1,'삼성전자','2022-08-19','COMPLETED','삼성전자 하반기 면접 스터디','2022-09-01','매주 화, 목','개인 자기소개서 기반으로 모의 면접 진행할 예정입니다.','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/samsung.jpg',5,'삼전면스',1,'COM'),(2,'KB국민은행','2022-08-23','RECRUITING','국민은행 IT 직무 면스 구해요','2022-09-04','월수금','국민은행 1차면접 대비 직무면접 스터디입니다.','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/kbbank.jpeg',4,'국민은행 뽀개기',NULL,'COM'),(3,NULL,'2022-08-24','RECRUITING','7급 공무원 국가직 면접스터디','2022-09-07','매주 토요일','같이 인성면접 준비해요! 시간 약속 철저하신분 모십니다.','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/official.png',4,'취뽀하자',NULL,'FREE'),(4,'쏘카','2022-08-22','RECRUITING','쏘카 2차 임원면접 스터디','2022-08-31','매주 화,금','쏘카 2차 임원면접 스터디입니다. 적극적으로 참여하실분들 모십니다.','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/socar.jpeg',5,'쏘카쏘카',NULL,'COM'),(5,NULL,'2022-08-25','RECRUITING','개발자 CS면접 스터디','2022-09-25','일주일에 한 번','프로그래밍 기본, 네트워크, 운영체제, 자료구조, DB, 알고리즘 등을 다룰 예정입니다. 같이 배우면서 발전하실분','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/techinterview.png',5,'CS정복',NULL,'FREE'),(6,'네이버','2022-08-21','RECRUITING','네이버 기술직군 신입 공채 스터디','2022-08-29','매주 월, 화, 수','하반기 네이버 공채 IT 기술직군 면접 스터디입니다. 상세 시간은 같이 조율해봐요','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/naver.png',5,'네이버 가즈아',NULL,'COM');
/*!40000 ALTER TABLE `recruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regist_resume`
--

DROP TABLE IF EXISTS `regist_resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regist_resume` (
  `regist_no` bigint NOT NULL AUTO_INCREMENT,
  `resume_no` bigint DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  PRIMARY KEY (`regist_no`),
  KEY `FK3lbqgtinetxie1lc68mfwo4s8` (`resume_no`),
  KEY `FKdh2fhu05tkc5kffqw5lv91afa` (`std_no`),
  CONSTRAINT `FK3lbqgtinetxie1lc68mfwo4s8` FOREIGN KEY (`resume_no`) REFERENCES `resume` (`resume_no`),
  CONSTRAINT `FKdh2fhu05tkc5kffqw5lv91afa` FOREIGN KEY (`std_no`) REFERENCES `study` (`std_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regist_resume`
--

LOCK TABLES `regist_resume` WRITE;
/*!40000 ALTER TABLE `regist_resume` DISABLE KEYS */;
/*!40000 ALTER TABLE `regist_resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `resume_no` bigint NOT NULL AUTO_INCREMENT,
  `resume_title` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`resume_no`),
  KEY `FK1t4c44ugjhn4que09j97uwvhd` (`user_no`),
  CONSTRAINT `FK1t4c44ugjhn4que09j97uwvhd` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,'삼성전자 자기소개서',1),(2,'삼성 하반기 자기소개서',2),(3,'하반기 삼성 자기소개서',3),(4,'삼성 자소서',4),(5,'삼성전자하반기 자기소개서',5),(6,'국민은행 자기소개서',1),(7,'현대 오토에버 자기소개서',2),(8,'LG CNS 자소서',3),(9,'롯데정보통신 자소서',4),(10,'인바디 자기소개서',5),(11,'우리은행 자개소개서',1),(12,'이마트 자기소개서',2),(13,'현대오토에버 자기소개서',3),(14,'국민은행 자기소개서',4),(15,'롯데 정보통신 자소서',5);
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume_detail`
--

DROP TABLE IF EXISTS `resume_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume_detail` (
  `detail_no` bigint NOT NULL AUTO_INCREMENT,
  `answer` varchar(1000) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  `item_no` bigint DEFAULT NULL,
  `resume_no` bigint DEFAULT NULL,
  PRIMARY KEY (`detail_no`),
  KEY `FKkxn2qsvwyroau1mi5id3qpfny` (`resume_no`),
  CONSTRAINT `FKkxn2qsvwyroau1mi5id3qpfny` FOREIGN KEY (`resume_no`) REFERENCES `resume` (`resume_no`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume_detail`
--

LOCK TABLES `resume_detail` WRITE;
/*!40000 ALTER TABLE `resume_detail` DISABLE KEYS */;
INSERT INTO `resume_detail` VALUES (1,'•	\"생산의 모든 부분을 알고 있는 Generalist\"\n\n삼성전자에 지원을 한 이유는 제가 궁극적으로 생각했던 생산관리 직무에서의 모든 부분의 업무를 수행할 수 있고 All around player로 성장하여 전문가가 될 수 있기 때문입니다.\n\n대학교 2학년 때, 데이터베이스 응용 및 설계 과목을 들으면서 SQL을 통해 데이터를 추출하는 것에 가장 흥미를 느껴 A+라는 성적을 받았고, 생산관리 업무에서도 SQL을 접하여 데이터를 다루는 업무를 꿈 꿨습니다. 현재는 OOO에서 기체 부품 생산관리 업무를 통해 생산기획, 원자재 사용 최적화, 생산량 달성을 수행했습니다. 이러한 경험과 제 꿈을 합쳐 업무를 수행할 수 있는 곳이 삼성전자라고 판단이 되었습니다.\n\n입사 후에는 장기적으로 같은 팀의 후배들과 유관부서들의 실무자에게 존경 받고 가장 배울 점이 많은 선배로 인식되는 것이 꿈입니다. 같이 일하는 사람들로부터 인정받으면 업무에 더 강한 동기가 부여되기 때문입니다. 이를 위해서 신입사원으로 입사하면 유관부서, 상사, 선배분들의 업무 태도, 방식, 커뮤니케이션을 면밀히 관찰하여 제 것으로 만들 것입니다. 기존에 해왔던 경험이 조직에 빠르게 적응할 수 있도록 하기 때문입니다. 또한, OJT를 통해 업무 습득은 물론이고 업무 외적인 시간을 최대한 활용하여 빠르게 회사 및 직무 이해도를 넓히겠습니다.\n','•	Q1.삼성전자를 지원한 이유와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오.',1,1),(2,'\"코로나 문제, 모든 SCM의 문제가 생긴다\"\n\n최근 사회이슈 중 중요하다고 생각되는 한 가지는 현재 코로나 바이러스로 인한 제조업계의 SCM 문제입니다. 해당 이슈를 선정한 이유는 현재 OO에서 생산관리 담당자로 일하면서 코로나 바이러스로 인해 많은 원자재 업체들이 문을 닫아 원자재 수급에 문제를 겪었고, 고객사마저 PO를 줄이면서 많은 현장 인원들의 유휴 시간이 발생한 경험이 있기 때문에 항상 제조업체는 질병 문제에 대해 경각심을 가지고 대비해야 된다고 생각했습니다. 현재 항공 여객 운송업을 하는 회사들은 항공기 제작업체로부터 항공기를 구매하고, 항공기 제작업체는 현재 제가 일하고 있는 OO으로부터 항공기에 들어가는 부품을 수급 받고 있습니다. 코로나 바이러스로 인해 여행을 다니는 승객 숫자가 많이 줄어서 항공업계 문제가 장기화될 것, 항공 산업이 항공 운송을 통해 조금씩 살아나고 있고 오히려 기회가 되어 금방 살아날 것을 바라보고 있는 쟁점이 있습니다.\n\n하지만, 조금 다른 관점으로 이슈를 바라보았습니다. 단면적으로만 봤을 때는 여객 운송업은 짧은 시간 동안은 화물을 운송하면서 문제를 해결할 것 처럼 보이나, 항공 산업에는 하기와 같이 많은 SCM 생태계가 연결이 되어 있습니다.\n\n여객 운송업 - 항공기 제작 업체(OO, OOOO) - 항공기 부품 제작업체 - 원자재 생산 업체\n\n이러한 연결에서 한 곳이라도 문제가 발생하면 다른 업체가 영향을 받는 것이 분명하기에 전체적인 큰 그림을 가지고 바라볼 필요가 있다고 생각합니다. 다른 제조업체도 크게 다를 바가 없다고 생각합니다. 삼성전자에서 제가 맡은 제품을 단순히 생산해서 일정 안에 납품한다는 생각을 더 넓혀 가겠습니다. 원자재부터 고객에게 납품하는 그 순간까지 하나의 연결고리라고 생각하고, 또 다른 질병 문제가 터지면 생산관리 담당자로서 영향 분석을 하며 회사에 손해가 발생하지 않도록 대비하고 싶습니다.\n','•	Q2.최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고 이에 관한 자신의 견해를 기술해 주시기 바랍니다.',2,1),(3,'\"2년동안 생산관리 업무로 기본기를 준비했습니다\"\n\nOO에서 항공기 기체 부품 생산관리 업무를 수행했습니다. 약 2년 정도 되는 경험을 통해 SAP의 사용법, 제품의 BOM을 보는 법을 지식으로 가지고 있습니다. 다수의 사업을 관리하면서 월별 & 연간 생산 계획을 수립하고, 제품에 들어가는 하위 자재들 수급 관리하며, 유휴 시간이 없도록 적정 재고를 보유하며 적시에 투입을 했던 경험을 보유하고 있습니다.\n\n\"OO 사업을 통해 다양한 업무를 해보다\"\n\n생산관리 직무에 적합하다고 생각하는 이유는 현 직장에서 생산관리 업무를 수행하면서 연간 생산 계획이라는 큰 그림을 먼저 그리고 목표를 달성하기 위해 세부적인 일정마다 문제점을 해결해주며 목표를 향해 달려나가는 일이 즐겁기 때문입니다.\n\nOO에서 OO 부품 생산관리 업무를 수행하면서 다음과 같은 업무를 수행하였습니다.\n\n- 생산계획 수립 및 자재 수급 관리: 생산 Lead time을 기반으로 하여 월부터 ~ 1년의 생산 계획을 수립해 보았습니다. 또한 제품에 들어가는 하위 자재 관리 담당자와 지속적으로 소통하며 수급에 문제가 없는 지 확인하고, 생산 Line이 멈출 거 같으면, 운송 형태를 변경하기도 하였습니다.\n\n- 제품별 생산 진도 관리: 생산관리 업무를 하면서 하루의 시작은 회사 내부의 시스템을 통해 각 제품 별 진척이 잘 되고 있는지 체크하고 진척이 안 되고 있는 제품이 있다면 현장에 방문하여 조장님들의 의견을 듣고 유관부서에게 연락을 통해 문제를 해결하고 정체 시간을 줄일 수 있도록 노력했습니다.\n\n- 자재 불출 process 변경을 통한 시간 단축: 협력업체로부터 입고 되는 자재를 창고에서 특정 시간에만 입고를 시켜주기에, 전산에 하루 뒤에 입고가 되었습니다. 이런 문제를 해결하고자 담당자가 직접 관리하는 방법으로 변경하여 기존에 지연되었던 시간들을 줄일 수 있었습니다.\n','•	Q3.직무 관련 본인이 갖고 있는 전문지식/경험을 작성, 이를 바탕으로 지원 직무에 적합한 사유를 서술해 주시기 바랍니다.',3,1),(4,'•	저는 지식의 전달을 통해 새로운 자극을 주고 타인의 성장에 기여하는 것에 성취감을 느낍니다. 교육학, 교육공학을 복수전공 하며, 개인의 역량 신장 뿐 아니라 기업의 생산성까지 영향을 미칠 수 있다는 점에서 HRD 직무에 매력을 느꼈습니다. 교육적 가치를 기업현장에 전달하는 HRD 직무야말로 제가 성취감을 느끼며 일할 수 있는 직무라고 생각했습니다. 성공적인 디지털 트렌스포메이션을 위해 HRD 담당자로서, 그룹의 가장 선두에서 임직원들에게 필요한 역량과 스킬을 적시에 제공할 수 있어야 한다고 생각합니다.\n대체불가능한 HRD전문가로 성장하고 싶습니다. HRD의 역할은 교육을 통해 변화를 유도하여 궁극적으로 성과를 개선하는 것이라 생각합니다. 이를 위해 신입 초기에는, 교육 운영 경험을 바탕으로 선배님들의 교육 운영 업무를 적극적으로 지원 하며 교육 현장에 빠르게 적응하겠습니다.\n','•	Q1.삼성전자를 지원한 이유와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오.',1,2),(5,'전 산업군 디지털 트렌스포메이션이 발생하고, 에듀테크 역시 HRD계 핫 이슈입니다. 학습자의 시선, 페이지 잔류 시간 등 학습을 실시간 분석하여 적응적 지원을 돕는 학습분석 툴, 시뮬레이션을 통해 위험상황 대처법 학습이 가능한 VR 등 에듀테크는 그간 기업교육에서의 어려움을 덜어줄 효과적인 도구이자 HRD 이슈입니다. 하지만 저는 에듀테크가 학습자에게 교육 내용을 전달하는 효과적 방법 중 하나일 뿐, 최적의 솔루션인지는 고려해보아야 한다고 생각합니다. 다량의 실시간 학습데이터, 학습자, 교육콘텐츠를 다룬다는 장점이 있지만, 예상 발 생 비용, 학습자들의 혼란 등 여러 발생가능 문제가 있습니다. 교육은 백년지대계라는 말이 있듯, HRD 담당자라면, 트렌드를 무조건 따를 것이 아니라, 교육담당자 간의 자체 분석을 통해, 삼성전자에 가장 최적화된 에듀테크를 효율적으로 취사선택할 수 있어야 한다고 생각합니다.','•	Q2.최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고 이에 관한 자신의 견해를 기술해 주시기 바랍니다.',2,2),(6,'\"교육 전공, 3번의 인턴십, 미래 HRD전문가를 꿈꾸는 준비된 인재\"\n\n저는 타인의 성장에 이바지함으로써 성취감을 느낍니다. 그래서 제 능력으로 많은 사람을 도울 방법을 고민했고, 교육이야말로 이를 실천할 가장 가치 있는 방법이라 생각해 교육학과에 진학했습니다. 교육과 교육공학을 공부하며, 교육이 개인의 역량 신장 뿐만 아니라 나아가 기업의 생산성에도 영향을 미친다는 것을 알게 되어 자연스럽게 HRD에 관심을 가지게 되었습니다.\n\n장래 HRD전문가를 꿈꾸며 해당 직무를 잘 수행하고자 2가지 노력을 했습니다.\n먼저, 교육 프로그램 기획력을 기르기 위해 여러 수업에서 직접 교육 프로그램을 기획해보았습니다. ‘교수체제개발’ 수업에서 팀 프로젝트로 임직원 마음 건강 프로그램을 기획했습니다. 현업의 교육 프로그램 개발 단계에 따라 수강생 선정 기준부터 신뢰도 높은 교육 효과성 평가를 선정하기까지의 과정을 경험할 수 있었습니다. ‘이러닝개발’ 수업에서는, 22명의 실제 카페 파트 타이머 대상 요구조사 설문을 진행하여, 신입 파트타이머 교육 시 발생하는 비체계적 인수인계와 실습 부족을 해결하기 위한 E-러닝 프로그램을 제작했습니다. ‘HRD 컨설팅 제안서 개발’ 수업에서는, 역량 개념과 경영 컨설팅 Frame을 활용하여 `52시간 근무제`에 대응하는 스마트 워킹 제도와 제도 정착을 위한 교육 프로그램 제안서를 작성해 보았습니다.\n\n나아가 수업에서의 배움에 그치지 않고, 세 번의 인턴십을 통해 HRD실무를 경험했습니다. 교육 프로그램이 실제로 운영되는 현장에서 근무하며, 꼼꼼한 교육 운영 능력과, 학습자를 향한 고객 지향성을 길렀습니다. 또한, 결과 보고서를 작성하며, “현업 적용 가능성이 큰 실습 과정과 교육 콘텐츠가 학습자의 만족도를 높인다.” 와 같이 교육 프로그램 기획 시 유용할 통찰력을 얻을 수 있었습니다. 전공 지식과 실무 경험을 기반으로 임직원에게 필요한 교육 프로그램을 설계하여 궁극적으로 성과 향상에 기여하겠습니다.\n','•	Q3.직무 관련 본인이 갖고 있는 전문지식/경험을 작성, 이를 바탕으로 지원 직무에 적합한 사유를 서술해 주시기 바랍니다.',3,2),(7,'•	\"반도체 한 길만 바라보고 걸어온 나\"\n\n모든 반도체는 Packaging으로 통한다고 생각합니다. 4차 산업혁명을 이끄는 기술은 Packaging이라고 생각합니다. 반도체 패키지 개발 및 양산에 이바지하여 반도체 후공정 기술을 세계 최고 수준으로 향상해보고 싶어서 TSP 총괄에 지원하게 되었습니다.\n\n반도체 제품의 다기능, 고성능화를 이해하기 위해 ‘반도체장비설계’, ‘반도체제조공정’ 수업을 통해 반도체 전반의 이해를 높였습니다. 최적의 전력전송회로를 찾기 위해 Defect를 찾아내고 이러한 결점을 개선하기 위한 개발과 Simulation 활동을 통해 Packaging 엔지니어의 자질을 길렀습니다.\n\n입사 후 저의 목표는, 10년 이내 아래 3가지를 겸비하여 삼성이 세계 일류 반도체회사로 나가는데 함께 하고 싶습니다.\n\n소통하는 나\n적극적인 소통으로 팀원들과 교류하고 발전하기 위해 노력하겠습니다. 항상 적극적인 자세로 교류하고 배우며 발전을 통해 혁신적인 업무 성과를 이루겠습니다.\n\n도전하는 나\n현 기술에 안주하지 않고, 끊임없이 노력하고 도전하여 회사 발전에 이바지한 공로로 받는 \'자랑스러운 삼성인상\'을 받고자 노력하겠습니다.\n\n고객 존중의 나\n항상 고객의 Needs를 파악하고, 고객의 이익과 만족을 먼저 생각하여 속도와 용량을 향상한 차세대 라인업 출시를 위해 지속해서 힘을 쏟겠습니다.\n','•	Q1.삼성전자를 지원한 이유와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오.',1,3),(8,'•	\"반도체산업 기술 초격차\"\n\n반도체 산업은 기술 격차가 6개월 정도 앞서면 독식할 수 있는 구조입니다. ‘반도체 굴기’에 나선 중국이 반도체 산업에 대규모로 투자하고 있습니다. 5년 전만 해도 LCD 시장은 한국기업의 독무대였지만 현재 중국이 그 자리를 차지하고 있습니다. 현재 중국이 세계 시장에서 노리는 것이 반도체 사업입니다. 중국은 현재 2025년까지 반도체 산업에 1조 위안, 한화로 약 170조 원의 지원금을 확보하였습니다. 현재 5년 정도의 기술력의 차이가 있다고 평가되는 중국의 반도체산업은 그 전망을 앞당기고 있습니다.\n\n향후 반도체 시장에서 우위를 지속하기 위해서는 비메모리 반도체의 경쟁력을 확보해야 합니다. 지속적인 반도체 시설 투자와 숙련된 기술인력 확보를 통한 기술개발에 노력해야 한다고 생각합니다. 또한, 고객 요구를 충족하기 위해 속도와 용량을 향상한 차세대 라인업 출시를 위해 지속해서 힘을 쏟는 것이 필요하다고 생각합니다. 비메모리 반도체는 메모리보다 복잡하고 어려워 선두 업체와 격차가 큰 분야입니다. 한 번 입지를 다져놓으면 수익성, 안정성이 모두 보장된다고 생각합니다. 사업성의 다양화를 통해 메모리 반도체산업의 쇼크에도 흔들리지 않는 기업이 될 수 있다고 생각합니다.\n','•	Q2.최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고 이에 관한 자신의 견해를 기술해 주시기 바랍니다.',2,3),(9,'•	\"모든 반도체는 Packaging으로 통한다!\"\n\nPackaging 기술은 반도체의 새로운 가치를 부여하고 있습니다. 저는 패키징 기술을 반도체 산업의 꽃이라고 생각합니다. 반도체 제품이 다기능, 고성능화되며 입출력(I/O)이 늘어나게 되면서 기존 기술로는 구현하지 못하는 한계에 이르며 패키징 기술의 역할이 커지고 있습니다.\n\n저는 Package Design 엔지니어 직무를 수행하기 위해서는 고효율의 전력전송을 위한 Simulation 활동과 함께 반도체 전반의 이해가 필요하다고 생각합니다.\n\n첫째, 고효율의 전력전송을 위한 수천 번의 Simulation 활동입니다.\n저는 최적의 전력전송회로를 찾기 위해 Defect를 찾아내고 이러한 결점을 개선하기 위한 개발과 Simulation 활동을 통해 Packaging 엔지니어의 자질을 길렀습니다.\n\n둘째, 반도체 전반의 이해도입니다.\n저는 반도체 학습 욕구를 반도체 관련 수업을 찾아 들으며 해결했습니다. ‘반도체장비설계’ 수업을 들었고 현재 ‘반도체제조공정’ 수업을 듣고 있습니다. ‘반도체장비설계’ 수업 때 FAB실에서 공정교육을 받았습니다. FAB실에서 Photo, Deposition, Etching 공정을 실습해보았습니다. 이를 통해 60시간의 훈련시간을 인정받아 NCS수료증을 취득하였습니다. 이번 SEMICON Korea에 참석하여 반도체 최신 이슈부터 업계 동향과 최신 기술을 파악할 수 있었습니다.\n','•	Q3.직무 관련 본인이 갖고 있는 전문지식/경험을 작성, 이를 바탕으로 지원 직무에 적합한 사유를 서술해 주시기 바랍니다.',3,3),(10,'•	\"데이터를 통한 초기 수율 확보\"\n\n1학년 때 산업공학 동아리에서 ‘앙상블 학습을 통한 DRAM 모듈 출하 품질보증 검사 불량 예측’ 논문을 분석하면서 반도체 품질 관리 기법에 관심이 생겼습니다. 이후 품질공학 강의에서 반도체 데이터 특징과 데이터 판별법을 공부하며 안정된 수율 확보가 곧 반도체의 경쟁력임을 알았습니다. 전공을 수강하며 공정에서 사용하는 품질 관리의 이론적 지식을 학습했습니다. 또한, 동아리에서 다양한 예측 모델 수립 프로젝트를 수행하며 오버피팅 등 발생하는 이슈를 이해하고 해결 방안을 모색했습니다. 이 경험을 통해 같은 데이터도 분석 목적과 방식에 따라 가치가 달라질 수 있다는 것을 배웠습니다.\n\n장기현장실습생으로 Flash PE팀에서 ‘머신러닝 기반 불량 예측 모델 제작’ 프로젝트를 수행하며 반도체 공정 데이터의 활용 가능성이 무궁무진하다고 느꼈습니다. 특히 양산 초기 데이터를 통해 불량 원인을 분석하여 수율을 안정화 하는 일이 매력적이었습니다. 이로부터 현업에 대한 이해를 바탕으로 초기 공정에서 불량 원인을 찾는 모델을 제작함으로써 수율을 확보하고 싶다는 목표가 생겼습니다. 삼성전자는 메모리 반도체 시장을 선도하며 ‘초격차’ 확보를 위해 끊임없이 도전합니다. 상기 경험을 바탕으로 입사 후 삼성전자의 일원으로 데이터를 통해 양산 초기 수율을 확보함으로써 ‘세계 최고 메모리반도체’를 함께 만들겠습니다.\n','•	Q1.삼성전자를 지원한 이유와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오.',1,4),(11,'•	이미 시작된 New Normal, ESG\"\n\n2020년 말부터 환경, 사회, 지배구조를 지칭하는 ESG에 대한 대중들의 관심이 높아졌습니다. 특히 지속 가능한 발전을 추구하며 환경 보호에 대한 관심이 갈수록 많아지고 있습니다. 이를 포스트 코로나 시대의 새로운 일상, New normal(뉴 노멀)이라고 지칭합니다. 뉴 노멀 시대에 ESG 중심 소비와 투자 비중은 더욱 커질 전망이며, 제조업 기반의 삼성전자는 이 이슈에 더 민감할 수밖에 없다고 생각합니다. 대학교에 입학하고 자취를 처음 시작했을 때 정수기를 설치할 수 없어 생수를 사 마셨습니다. 하지만 생수는 플라스틱 쓰레기를 발생시키고, 이를 재활용하는 것도 쉽지 않아 환경 문제를 야기한다는 뉴스를 보았습니다. 그 이후 `OOO`라는 필터 교체형 정수기를 이용함으로써 생수 배달을 끊었습니다. 실제로 환경에 대한 경각심이 커진 이후로 OOO 사용 인구는 꾸준히 늘고 있습니다.\n\n비슷한 일례로, 팬데믹 사태로 일회용품 사용이 급증하면서 환경에 대한 대중의 경각심이 높아졌습니다. `텀블러 들고 다니기` 등의 개인적인 실천뿐 아니라, 기업에 실질적인 액션을 요구하는 등 기업의 ESG 실천에 대한 기준이 높아지고 있습니다. ESG 경영으로의 개편은 이미 전세계적 트렌드가 되었습니다. 특히 MZ 세대 사이에서 ESG는 제품의 선택만이 아니라 기업을 선택하는 기준 중 하나가 되었습니다. 삼성전자는 이전부터 지속 가능 경영을 내세울 뿐 아니라 2021년 6월, 반도체 업계 최초로 전 사업장에 탄소, 물, 폐기물 저감 인증을 획득하였고, 꾸준히 저전력 반도체를 개발하는 등 지속 가능 경영의 실질적인 성과를 내고 있습니다. 하지만 이에 만족하지 않고, ESG 비전을 확실시 하고, 사내 ESG 중심 활동을 고착화 시키는 것이 필요하다고 생각합니다. ESG 중심의 사내 문화를 바탕으로 혁신적인 기술 개발을 이어간다면 삼성전자는 제조업이라는 태생적 불리함을 이겨내고 뉴 노멀 시대에도 꾸준히 좋은 성과를 낼 수 있을 것입니다.\n','• Q2.최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고 이에 관한 자신의 견해를 기술해 주시기 바랍니다.',2,4),(12,'•	\"데이터 의미 기반의 인사이트 발굴\"\n\n적절한 평가를 수행하려면 공정과 데이터에 대한 이해를 바탕으로 이슈에 빠르게 대응하고 문제를 해결해야 합니다. 그래서 품질공학, 데이터마이닝 등 강의를 통해 수율 향상을 위한 품질 관리 기법과 통계 기반 데이터 분석 이론을 공부했습니다. 동시에 3년간 동아리에서 R과 파이썬으로 다양한 예측 모델 수립 프로젝트를 수행하며 데이터 분석 시 나타나는 문제를 이해하고 분석 기법을 학습했습니다. ‘마케팅 성공 여부 예측’ 프로젝트에서 accuracy 향상을 목표로 다양한 분석 기법을 적용했지만 타겟 accuracy를 일정 수준 이상으로 학습할 경우 오버피팅이 발생해 오히려 성능이 나빠졌던 적이 있습니다. 이를 해결하기 위해 문제 원인을 분석한 결과, 학습 데이터에 타겟 값과 상관관계가 낮은 파라미터가 포함된 것이 원인이었습니다. 그래서 상관관계가 낮은 파라미터를 소거한 후 data regularization을 진행했습니다. 그 결과, 평균 accuracy가 약 2% 상승한 모델을 구축했습니다. 이를 통해 데이터 분석 시 데이터가 가진 의미 파악이 우선이 되어야 함을 배웠습니다.\n\n이후, 삼성전자에서 공정 데이터를 이용한 ‘머신러닝 기반 불량 예측 모델 제작’ 실습에 참여했습니다. 불량 데이터의 의미를 파악하려면 반도체 공정에 대한 이해가 필요합니다. 따라서 제일 먼저 낸드플래시 제조 공정과 데이터의 특징을 공부했습니다. 그로부터 불량 데이터의 비율이 매우 낮다는 특성을 파악해 문제를 ‘High Imbalanced Data 처리’, ‘오버피팅 해결 ’로 단순화할 수 있었습니다. 이상치 데이터의 영향을 줄이기 위해 현업에서 사용하는 Grading 등 기법을 공부해 소수의 문제가 되는 파라미터만 선별해 예측 모델을 수립할 수 있었습니다. 상기 경험을 바탕으로 삼성전자에 입사한 이후, 반도체 공정과 데이터 특성을 꾸준히 공부하고, 이를 바탕으로 불량 원인을 분석해 수율 향상에 기여하겠습니다.\n','•	Q3.직무 관련 본인이 갖고 있는 전문지식/경험을 작성, 이를 바탕으로 지원 직무에 적합한 사유를 서술해 주시기 바랍니다.',3,4),(13,'온라인 유통 채널에서의 고객 경험을 혁신하는 Sales & Marketing 전문가로 성장하기 위해 국내영업 직무에 지원하였습니다. 삼성전자는 고객과의 최접점에서 데이터를 확보할 수 있는 D2C 플랫폼 \'삼성닷컴\'을 보유하고 있기에, 고객 데이터 기반의 온라인 판매 전략을 기획하며 성장할 수 있을 것이라 확신했습니다.\n\n지난 1년간 온라인 마케터로 일하며 디지털 환경과 데이터에 큰 관심을 가지게 되었습니다. 고객이 어떤 키워드를 검색해 사이트로 유입되었고, 어떤 경로로 이동하고 이탈했는지 등의 구매 여정을 실시간으로 파악해 대응하는 과정에서 즐거움을 느꼈기 때문입니다. 이러한 데이터는 지속 축적되며 새로운 전략 수립의 근거가 된다는 점에서 매 순간 일의 보람을 느낄 수 있었습니다. 영업인은 기업의 비전을 실적으로 만드는 역할을 한다고 생각합니다. 가전, 전자기기 등 고관여 제품의 비대면 판매가 확대되는 트렌드 속에서, 온라인 구매 여정에 대한 이해를 바탕으로 고객의 이탈을 막고 전환율을 극대화할 수 있는 전략을 수립하겠습니다.\n\n','•	Q1.삼성전자를 지원한 이유와 입사 후 회사에서 이루고 싶은 꿈을 기술하십시오.',1,5),(14,'•	기존과는 다른 용도로 오프라인 매장을 활용하는 \'다크 이코노미\'가 유통업계의 트렌드로 떠오르고 있습니다. 물류 창고만 있는 매장인 \'다크 스토어\'와 주방만 있는 식당인 \'다크 키친\'이 대표적인 형태입니다. 한 예로, OOOO는 온라인 배송을 강화하기 위해 매장 일부를 물류 창고로 전환하였고, 이렇게 다크 스토어를 구축한 매장들은 67%의 온라인 매출 증가율을 기록하였습니다. 다크 이코노미는 팬데믹 시대에 대응하는 오프라인 매장의 생존 전략인 것입니다. 그러나 매장 규모를 줄이는 다크 이코노미만이 유일한 대응 전략은 아닐 것입니다. 오프라인 매장이 가지는 분명한 강점이 존재하기 때문입니다. OOOO의 \'OOOOO\'가 좋은 예시입니다. OOOOO는 가전제품이 단순 진열된 기존 형태에서 벗어나, 반려동물을 목욕시킬 수 있는 \'펫스파룸\', 유튜브 촬영이 가능한 \'미디어존\' 등을 갖춘 체험형 공간으로 변화했습니다. OOOOO로 리뉴얼 오픈한 잠실점은 전년 대비 30%의 매출 증가를 기록하며 새로운 가능성을 시사했습니다.\n\n이처럼 고객 경험 제공은 오프라인 매장의 경쟁력이 될 수 있습니다. 중요한 것은 매장별 방문자 특성을 반영해 각 매장만의 특색 있는 공간을 설계하는 것입니다. 따라서 오프라인 매장에서도 고객 데이터 확보를 위한 인프라 마련이 필수적입니다. 예를 들어, 방문 고객의 동반자 구성이나 매장 내 동선 등 오프라인 고객 데이터 확보를 위해서는 AI 카메라나 안면인식 등 센싱 기술의 활용이 필요합니다. 이러한 흐름을 오프라인 직영점인 삼성 디지털프라자에 적용해볼 수 있습니다. 비교적 높은 충성도를 가지고 있는 고객이 주로 방문한다는 점을 고려해, 멤버십 가입자 대상 체험 공간을 조성해 주기적인 재방문을 유도할 수 있습니다. 코로나 이후에도 이어질 비대면 소비 패턴에 따라, 보안·인증 기술을 적용해 심야 시간에는 무인으로 운영하는 하이브리드 매장으로 전환한다면 추가 매출 확보도 가능할 것입니다.\n','•	Q2.최근 사회이슈 중 중요하다고 생각되는 한가지를 선택하고 이에 관한 자신의 견해를 기술해 주시기 바랍니다.',2,5),(15,'팬더믹 상황에 맞는 효과적인 온라인 홍보 방안을 기획해, 한정된 예산으로 높은 효과를 거둔 경험이 있습니다. 기존에는 신입 리크루팅을 위해 오프라인 홍보를 진행해왔지만, 비대면 수업으로 학교 방문 인원이 줄어든 상황에서 새로운 온라인 홍보 방안을 모색해야 했습니다. 당초 학회 SNS 계정의 팔로워를 추첨해 굿즈를 제공하는 이벤트를 기획했지만, 리크루팅 장소 대여에 예상치 못한 비용이 지출되며 굿즈 제작이 어려워졌습니다. 학회원들은 줄어든 예산에 맞춰 품목을 바꾸거나 저렴한 기프티콘을 제공하자고 했지만, 경품의 매력도가 떨어진다면 목표로 했던 참여자 수 100명을 달성하기 어려울 것이라 생각했습니다.\n\n저는 생각을 바꿔 비용 대비 효과를 극대화하자는 취지에서 \'학회원들이 직접 제작한 PPT 템플릿\'을 경품으로 제안했습니다. 하지만 다른 학회원들은 기프티콘 제공 시의 참여율이 높을 것이고, 템플릿을 새로 제작하는 것이 번거롭다며 반대했습니다. 저는 리크루팅 활성화라는 이벤트 목적을 고려했을 때 템플릿 지급이 효과적일 것이라는 점을 들어 학회원들을 설득하였습니다. 기프티콘을 받기 위해 팔로우한 학생들은 당첨자 발표 후 이탈할 가능성이 높지만, 공모전이나 수업 활용을 위해 템플릿을 원하는 학생들은 마케팅에 관심이 있어 학회에 지원할 유인이 높다고 생각했기 때문입니다. 또한, 기존에 공모전 제출을 위해 제작해뒀던 템플릿을 일부 수정해 활용함으로써 신규 제작의 번거로움을 해결하자고 제안한 결과, 학회원들을 설득할 수 있었습니다. 별도의 제작 비용이 들지 않아 참여자 전원에게 템플릿을 증정할 수 있었고, 100% 당첨이라는 입소문을 타며 200명 가량의 인원이 이벤트에 참여하였습니다. 결과적으로 해당 시즌 리크루팅은 역대 최고의 경쟁률을 기록했고, 성공적으로 신입 회원 모집을 마칠 수 있었습니다. 성과 달성 과정에서 어려움이 있더라도 전략적인 대안을 제시하고, 논리적으로 설득함으로써 문제를 해결하는 인재가 되겠습니다.\n','•	Q3.직무 관련 본인이 갖고 있는 전문지식/경험을 작성, 이를 바탕으로 지원 직무에 적합한 사유를 서술해 주시기 바랍니다.',3,5);
/*!40000 ALTER TABLE `resume_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `review_no` bigint NOT NULL AUTO_INCREMENT,
  `review_contents` varchar(255) DEFAULT NULL,
  `review_date` varchar(255) DEFAULT NULL,
  `review_title` varchar(255) DEFAULT NULL,
  `review_type` varchar(255) DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`review_no`),
  KEY `FKhctu9q3140kf2fsn2nfqr45ur` (`user_no`),
  CONSTRAINT `FKhctu9q3140kf2fsn2nfqr45ur` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'예상질문에 대한 대답을 잘 했지만 돌발 질문에 대한 대처는 조금 아쉬웠다. 준비를 더 철저하게 해야겠다.','2022-08-11','우리은행 모의면접 회고','FAKE',1),(2,'나의 필살기를 더 어필할 수 있는 답변을 짜야겠다. 결과가 어떻게 되든간에 마음 다잡고 프로젝트에 집중해야겠다.','2022-08-17','우리은행 2차면접 회고','REAL',1);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `std_no` bigint NOT NULL AUTO_INCREMENT,
  `com_name` varchar(255) DEFAULT NULL,
  `end_date` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `std_day` varchar(255) DEFAULT NULL,
  `std_detail` varchar(255) DEFAULT NULL,
  `std_img` varchar(255) DEFAULT NULL,
  `std_limit` int DEFAULT NULL,
  `std_name` varchar(255) DEFAULT NULL,
  `std_notice` varchar(255) DEFAULT NULL,
  `std_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`std_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (1,'삼성전자','2022-08-19','2022-09-01','매주 화, 목','개인 자기소개서 기반으로 모의 면접 진행할 예정입니다.','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/studyImg/samsung.jpg',5,'삼전면스',NULL,'COM');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_join`
--

DROP TABLE IF EXISTS `study_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_join` (
  `join_no` bigint NOT NULL AUTO_INCREMENT,
  `join_type` varchar(255) DEFAULT NULL,
  `resume_no` bigint DEFAULT NULL,
  `std_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`join_no`),
  KEY `FK2h7ee95r5q23fyfbe0ao8ed3x` (`std_no`),
  KEY `FKlew8jrkl45f71ny4lth2x0b96` (`user_no`),
  CONSTRAINT `FK2h7ee95r5q23fyfbe0ao8ed3x` FOREIGN KEY (`std_no`) REFERENCES `study` (`std_no`),
  CONSTRAINT `FKlew8jrkl45f71ny4lth2x0b96` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_join`
--

LOCK TABLES `study_join` WRITE;
/*!40000 ALTER TABLE `study_join` DISABLE KEYS */;
INSERT INTO `study_join` VALUES (1,'LEADER',1,1,1),(2,'NORMAL',2,1,2),(3,'NORMAL',3,1,3),(4,'NORMAL',4,1,4),(5,'NORMAL',5,1,5);
/*!40000 ALTER TABLE `study_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `user_email` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_img` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pw` varchar(255) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nooyig@naver.com','qkrrldbs','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/profileImg/kiyoonImage.jpeg','박기윤','$2a$10$YnkA5MP1t8xCgbuq1/HjYecxIjMOrbWyYJGfyTM9Ao.WefSuRo8te','USER'),(2,'dbstjrcks@naver.com','dbstjrcks','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/profileImg/chanImage.jpg','윤석찬','$2a$10$hXB7xotQReXhZ3yPOMS8l.URHhnuy726B0wVABKBUCMeSyGJZRaqW','USER'),(3,'dlwlsgod@naver.com','dlwlsgod','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/profileImg/hangImage.jpeg','이진행','$2a$10$UzimRPzix54DN.Ieoud.HuMNGLkNWXDoeiPMqZtnvqrYhrmL3sR06','USER'),(4,'tjdanswh@naver.com','tjdanswh','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/profileImg/MoonImage.jpeg','성문조','$2a$10$xSLdP./C0wM0YHZSvk90zuUKWTgkc4qUDNz..gpbnwgSBVTokDxna','USER'),(5,'rkdwkdgh@naver.com','rkdwkdgh','https://i7b105.p.ssafy.io/HOMETERVIEW/Image/profileImg/kangImage.jpeg','강장호','$2a$10$Vm8OR6DgHO0QiwQhDR4dne0MYml1sWXvhmM90zDKL7jdjndi.3j4W','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:44:22
