-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2024 at 09:25 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_master`
--

-- --------------------------------------------------------

--
-- Table structure for table `cancle_ticket`
--

CREATE TABLE `cancle_ticket` (
  `index_no` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `seat_no` varchar(50) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `date_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cancle_ticket`
--

INSERT INTO `cancle_ticket` (`index_no`, `user_name`, `phone_number`, `movie_name`, `seat_no`, `payment`, `status`, `date_time`) VALUES
(19, 'qqqq', '2345432123', 'HANUMAN', '1-2 4-5 ', '10', 'cancled', '2024-08-24 10:18:06'),
(21, 'rre', '8976543', 'MAIN ATAL HOON', '1-1 ', '4', 'cancled', '2024-08-24 10:18:06'),
(22, 'qqq', '3467876', 'SALAR', '1-1 ', '5', 'cancled', '2024-08-24 10:18:06'),
(23, 'yy', '9877', 'HANUMAN', '5-1 5-2 5-4 3-3 2-4 ', '25', 'cancled', '2024-08-24 10:18:06'),
(27, 'meet', '9727380258', 'SALAR', '5-5 5-4 3-3 ', '15', 'cancled', '2024-08-24 12:41:07'),
(28, 'jay', '3265987412', '12th FAIL', '1-1 1-2 ', '8', 'cancled', '2024-08-24 12:36:17');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `empID` int(11) NOT NULL,
  `eName` varchar(100) NOT NULL,
  `eCategory` varchar(100) NOT NULL,
  `eSalary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empID`, `eName`, `eCategory`, `eSalary`) VALUES
(1, 'meet', 'Box Office Staff', 20000),
(2, 'ghnj', 'Cleaning Staff', 30000),
(4, 'jay', 'Screen Management Staff', 1000000);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `food_name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `food_name`, `price`, `quantity`) VALUES
(1, 'Pop corn', 2, 550),
(2, 'Burger', 3, 696),
(3, 'Fries', 2, 951),
(4, 'Pizza', 5, 400),
(5, 'Sandwich', 4, 397),
(6, 'coke', 1, 1000),
(7, 'Sprite', 2, 600),
(8, 'pepsi', 1, 700),
(9, 'coffee', 3, 600),
(10, 'samosa', 2, 290);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `m_Id` int(10) NOT NULL,
  `m_Name` varchar(50) NOT NULL,
  `m_Pass` varchar(40) NOT NULL,
  `m_userName` varchar(50) NOT NULL,
  `m_phoneNo` varchar(50) NOT NULL,
  `m_work` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`m_Id`, `m_Name`, `m_Pass`, `m_userName`, `m_phoneNo`, `m_work`) VALUES
(1, 'mahesh', 'mahesh@9876', 'm9876', '9876789076', 'employee_management'),
(2, 'ramesh', 'ramesh@9345', 'r9345', '9345236788', 'food_management'),
(3, 'suresh', 'suresh@9123', 's9123', '9123987654', 'movie_management');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `index_no` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `seat_no` varchar(50) NOT NULL,
  `payment` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `date_time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`index_no`, `user_name`, `phone_number`, `movie_name`, `seat_no`, `payment`, `status`, `date_time`) VALUES
(15, 'pp', '98', 'HANUMAN', '1-1 ', '5', 'Booked', '2024-08-20  10:45:44'),
(24, 'a', '99', 'MAIN ATAL HOON', '2-2 3-3 ', '8', 'Booked', '2024-08-23  23:00:46'),
(25, 'a', '0000000000', 'SALAR', '1-1 ', '', 'cancled', '2024-08-24  10:08:05'),
(26, 'meet', '9727380258', 'SALAR', '5-5 5-4 ', '10', 'Booked', '2024-08-24  12:31:21'),
(29, 'jay', '3265987412', '12th FAIL', '1-1 1-2 3-3 ', '12', 'Booked', '2024-08-24  12:37:20'),
(30, 'meet', '9727380258', 'MAIN ATAL HOON', '4-5 ', '4', 'Booked', '2024-08-24  12:40:24');

--
-- Triggers `movie`
--
DELIMITER $$
CREATE TRIGGER `cancleticket` BEFORE DELETE ON `movie` FOR EACH ROW INSERT into cancle_ticket(index_no,user_name,phone_number,movie_name,seat_no,payment,status,date_time) values (old.index_no,OLD.user_name,OLD.phone_number,OLD.movie_name,OLD.seat_no,OLD.payment,'cancled',NOW())
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `movie_name`
--

CREATE TABLE `movie_name` (
  `index_no` int(11) NOT NULL,
  `movie_name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `movie_name`
--

INSERT INTO `movie_name` (`index_no`, `movie_name`, `price`) VALUES
(2, 'MAIN ATAL HOON', 4),
(3, 'SALAR', 5),
(4, 'DUNKI', 3),
(5, '12th FAIL', 4),
(6, 'kalki', 7);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(10) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `date_and_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `password`, `phone_number`, `date_and_time`) VALUES
(7, 'jay1234', 'qwert', '9898989898', '2024-08-22 08:56:17'),
(9, 'hhui', '56789', '0987654321', '2024-08-22 09:40:49'),
(10, 'wertyj', '`1234567', '1234567890', '2024-08-23 06:22:54'),
(11, 'ujm', '0909', '0909090909', '2024-08-23 06:25:08'),
(12, 'sagar123', 'Sagar@123', '9999955555', '2024-08-23 10:57:30'),
(13, 'a', 'a', '0000000000', '2024-08-23 12:25:36'),
(14, 'meet', '2486', '9727380258', '2024-08-24 06:55:37'),
(15, 'jay', '2684', '3265987412', '2024-08-24 07:05:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cancle_ticket`
--
ALTER TABLE `cancle_ticket`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`empID`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`m_Id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `movie_name`
--
ALTER TABLE `movie_name`
  ADD PRIMARY KEY (`index_no`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `empID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `manager`
--
ALTER TABLE `manager`
  MODIFY `m_Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `movie_name`
--
ALTER TABLE `movie_name`
  MODIFY `index_no` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
