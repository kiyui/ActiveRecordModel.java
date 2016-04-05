# ActiveRecordModel.java
My attempt at creating an Active Record implementation in Java to ease my suffering.

## Testing
Create a MariaDB database instance with the following Docker command: `docker run --name mariadb-java -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=java -p 3306:3306 -d mariadb`.
Dump the following SQL into it, using a `mysql` client or phpMyAdmin `docker run --name phpmyadmin-java -d --link mariadb-java:db -p 8080:80 phpmyadmin/phpmyadmin`

```
-- phpMyAdmin SQL Dump
-- version 4.6.0
-- http://www.phpmyadmin.net
--
-- Host: db
-- Generation Time: Apr 05, 2016 at 08:58 AM
-- Server version: 10.1.11-MariaDB-1~jessie
-- PHP Version: 5.6.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `first` varchar(255) NOT NULL,
  `last` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `first`, `last`) VALUES
(1, 'Micheal', 'Wong'),
(2, 'Jack', 'Knight'),
(3, 'John', 'Doe'),
(4, 'Katie', 'Doe'),
(5, 'Rosetta', 'Pebble');

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

CREATE TABLE `jobs` (
  `id` int(11) NOT NULL,
  `job` varchar(255) NOT NULL,
  `pay` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `job`, `pay`) VALUES
(1, 'Full-stack Developer', 200),
(2, 'Unemployed', 500),
(3, 'Beggar', 2000),
(4, 'Full-stack Developer Boss', 16000),
(5, 'Citizen under Bernie Sanders', 22000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jobs`
--
ALTER TABLE `jobs`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
```
