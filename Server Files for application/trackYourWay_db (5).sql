-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2016 at 02:48 PM
-- Server version: 5.5.43-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `trackYourWay_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `calender`
--

CREATE TABLE IF NOT EXISTS `calender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` int(11) NOT NULL,
  `title` varchar(40) NOT NULL,
  `details` varchar(500) NOT NULL,
  `eventLocation` varchar(100) NOT NULL,
  `longitude` double NOT NULL,
  `latitude` double NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `calender`
--

INSERT INTO `calender` (`id`, `college_id`, `title`, `details`, `eventLocation`, `longitude`, `latitude`, `date`, `time`) VALUES
(1, 1, 'Open day', 'Open day', '', 53.331008, -6.278773, '0000-00-00', '12:30:00'),
(2, 10, 'science fair', 'A science fair is going to be held on 21st of april', ' Cathal Brugha St, Dublin 1', 53.352102, -6.258361, '2016-04-21', '00:00:10'),
(3, 101, 'Job Fair', 'Job fair happening on atrium for the IT', '', 0, 0, '2016-04-27', '03:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `college_comment`
--

CREATE TABLE IF NOT EXISTS `college_comment` (
  `id` int(11) NOT NULL,
  `college_id` int(11) NOT NULL,
  `comments` int(245) NOT NULL,
  `date_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `college_course`
--

CREATE TABLE IF NOT EXISTS `college_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_college` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `remarks` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `duration` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `student id` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_college` (`id_college`),
  KEY `id_course` (`id_course`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `college_detail`
--

CREATE TABLE IF NOT EXISTS `college_detail` (
  `id` int(11) NOT NULL,
  `college_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `college_address` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `college_email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ratings` int(11) NOT NULL,
  `lattitude` double NOT NULL,
  `longitude` double NOT NULL,
  `contact` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `no_of_student` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `college_detail`
--

INSERT INTO `college_detail` (`id`, `college_name`, `college_address`, `college_email`, `ratings`, `lattitude`, `longitude`, `contact`, `type`, `no_of_student`) VALUES
(1, 'Griffifth college', 'South Circular Road, Dublin 8, Dublin.', '+353 1 415 04 00admissions@gcc.ie', 4, 53.331008, -6.278773, '+353 1 415 04 00', '', 111),
(10, 'Dublin Institute of Technology', '143-149 Rathmines Road Dublin 6 Co. Dublin Ireland', 'info@dit.ie', 5, -6.25945, 53.352576, '(01) 402 3000', 'Third Level', 2500),
(101, 'Dundalk Institute of Technology', 'dundalk', 'Info@dkit.ie', 4, 53.984727, -6.391472, '+353 (0) 42 9370200', 'Third Level', 400),
(102, 'National College of Ireland', '1 mayour street ,Ifsc dublin1', 'Info@ncirl.ie', 4, 53.348497, -6.242868, '019889999', 'third level', 500),
(112, 'Dublin City University', 'Glasnevin Dublin Co. Dublin Ireland', 'info@dcu.ie', 4, 53.385849, -6.257357, '(01) 700 5000', 'Third Level', 400),
(113, 'University College Cork', 'University College Cork , College Road, Cork', 'internationaloffice@ucc.ie', 0, -8.492811, 51.892223, '021 490 3000', 'University, third level', 12345);

-- --------------------------------------------------------

--
-- Table structure for table `college_ratings`
--

CREATE TABLE IF NOT EXISTS `college_ratings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `college_id` int(11) NOT NULL,
  `ratings` varchar(45) NOT NULL,
  `comment` varchar(40) NOT NULL,
  `date_time` datetime NOT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `college_id` (`college_id`),
  KEY `college_id_2` (`college_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `college_ratings`
--

INSERT INTO `college_ratings` (`id`, `college_id`, `ratings`, `comment`, `date_time`, `user_name`) VALUES
(1, 1, '4.3', 'good', '2016-04-01 00:00:00', 'hasan'),
(2, 10, '4.4', 'very good library', '2016-04-21 00:00:00', 'hasan'),
(3, 10, '1.2', 'asd', '2016-04-05 00:00:00', 'asd'),
(4, 102, '4.5', 'good', '2016-04-20 00:00:00', 'hasan'),
(5, 113, '4', 'amazing', '2016-04-13 00:00:00', 'anynamewilldo'),
(6, 1, '5', 'gs', '2016-04-06 00:00:00', 'esfef'),
(7, 1, '5', 'gs', '2016-04-06 00:00:00', 'Amazing Atmosphere');

-- --------------------------------------------------------

--
-- Table structure for table `course_detail`
--

CREATE TABLE IF NOT EXISTS `course_detail` (
  `id_Course` int(11) NOT NULL,
  `college_id` int(50) NOT NULL,
  `course_name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Course_level` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `course_type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Course_year` int(4) NOT NULL,
  `Keywords` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Fees` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_Course`),
  KEY `college_id` (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `course_detail`
--

INSERT INTO `course_detail` (`id_Course`, `college_id`, `course_name`, `description`, `Course_level`, `course_type`, `Course_year`, `Keywords`, `Fees`) VALUES
(101, 1, 'Business Administration', 'It is a bachelelor of bussiness course ', 'bachelor', 'third level', 4, 'Bussiness, Accounts', '4500'),
(102, 102, 'BA (Honours) in Business', '', '8', 'Full-time', 4, NULL, ''),
(103, 102, 'BA (Honours) in Human Resource Management', ' This full-time HR course draws on NCI’s sixty years as the leading educational institution for human resource management', '8', 'Full-time', 3, NULL, ''),
(104, 102, 'BSc (Honours) in Computing', NULL, '8 ', 'full time', 4, NULL, ''),
(105, 10, 'BA (Honours) in Accounting and Finance', NULL, '8', 'Full-time', 4, 'Finance', '5000'),
(106, 113, 'Arts (Applied Mathematics)', 'Applied mathematics is the application of mathematics to the modelling and solving of practical, real world problems. The emphasis throughout this three-year course is on developing and enhancing your problem-solving skills.', 'Level 8,  BA (Hons)', 'Full-time', 3, 'Mathematics, BA, BA(Honors), Maths', ''),
(107, 113, 'Applied Mathematics and Physics', 'BSc Applied Mathematics and Physics focuses on a) analytical and logical thinking in the application of mathematics and b) intuitive and analytical understanding of theoretical and experimental physics. ', 'Level 8, BSc (Hons)', 'Full-time', 4, 'Physics, Mathematics, Level 8, BSc (Hons)', '');

-- --------------------------------------------------------

--
-- Table structure for table `course_type`
--

CREATE TABLE IF NOT EXISTS `course_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `comments` varchar(45) NOT NULL,
  `level` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `course_type`
--

INSERT INTO `course_type` (`id`, `type`, `comments`, `level`) VALUES
(1, 'Full-time', '', '6'),
(2, 'Part-time', '', '7');

-- --------------------------------------------------------

--
-- Table structure for table `TestUser`
--

CREATE TABLE IF NOT EXISTS `TestUser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` text COLLATE utf8_unicode_ci NOT NULL,
  `Password` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `TestUser`
--

INSERT INTO `TestUser` (`user_id`, `UserName`, `Password`) VALUES
(1, 'Hassan', 'Password'),
(2, 'Hassan', 'Password');

-- --------------------------------------------------------

--
-- Table structure for table `UserDetails`
--

CREATE TABLE IF NOT EXISTS `UserDetails` (
  `user_id` int(7) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `UserEmail` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Name` varchar(35) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `age` text NOT NULL,
  `CertificatesAchieved` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `Interests` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `PrevCollege` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `PrevCourse` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_international` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UserEmail` (`UserEmail`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=107 ;

--
-- Dumping data for table `UserDetails`
--

INSERT INTO `UserDetails` (`user_id`, `UserName`, `Password`, `UserEmail`, `Name`, `age`, `CertificatesAchieved`, `Interests`, `PrevCollege`, `PrevCourse`, `is_international`) VALUES
(102, 'admin', 'admin123', 'admin@gmail.com', 'super admin', '23', NULL, 'bowling', 'DIT', 'Computing', 0),
(103, 'Hassan', 'Admin', 'djshakezz@yahoo.co.uk', 'Hassan', '23', 'electronics, 2d auto cad', 'programming,computer hardware', 'dun', 'cant remember', 0),
(104, 'sunny', 'shakya', 'sunny.shakya@ncirl.ie', 'sunny shakya', '23', 'BSCcsit', 'Sports', 'PMC', 'Computer science', 1),
(105, 'asd', 'asd', 'test@test.test', 'test', '22', ' nop', 'kj ', 'kjlkjlk', 'k ', 0),
(106, 'a', 'a', 'a@b.c', 'a', '1', 'hh', 'a', 'a', 'a', 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `calender`
--
ALTER TABLE `calender`
  ADD CONSTRAINT `calender_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college_detail` (`id`);

--
-- Constraints for table `college_comment`
--
ALTER TABLE `college_comment`
  ADD CONSTRAINT `college_comment_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college_detail` (`id`),
  ADD CONSTRAINT `college_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `UserDetails` (`user_id`);

--
-- Constraints for table `college_course`
--
ALTER TABLE `college_course`
  ADD CONSTRAINT `college_course_ibfk_1` FOREIGN KEY (`id_college`) REFERENCES `college_detail` (`id`),
  ADD CONSTRAINT `college_course_ibfk_2` FOREIGN KEY (`id_course`) REFERENCES `college_detail` (`id`);

--
-- Constraints for table `college_ratings`
--
ALTER TABLE `college_ratings`
  ADD CONSTRAINT `fk_college_id` FOREIGN KEY (`college_id`) REFERENCES `college_detail` (`id`);

--
-- Constraints for table `course_detail`
--
ALTER TABLE `course_detail`
  ADD CONSTRAINT `fk1` FOREIGN KEY (`college_id`) REFERENCES `college_detail` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
