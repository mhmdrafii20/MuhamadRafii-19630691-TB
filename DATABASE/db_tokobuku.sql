-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 08, 2021 at 10:00 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tokobuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_inputbuku`
--

CREATE TABLE `tb_inputbuku` (
  `kodetransaksi` varchar(50) NOT NULL,
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `tanggalinput` date NOT NULL,
  `jumlah` int(50) NOT NULL,
  `hargabeli` int(50) NOT NULL,
  `hargajual` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_inputbuku`
--

INSERT INTO `tb_inputbuku` (`kodetransaksi`, `kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `tanggalinput`, `jumlah`, `hargabeli`, `hargajual`) VALUES
('TR001', 'KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', '2021-04-03', 10, 100000, 25000),
('TR002', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-03', 10, 100000, 25000),
('TR003', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-10', 10, 100000, 0),
('TR004', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-10', 10, 100000, 25000);

--
-- Triggers `tb_inputbuku`
--
DELIMITER $$
CREATE TRIGGER `replacehargajual` BEFORE INSERT ON `tb_inputbuku` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET hargabuku=new.hargajual WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `updatestok` AFTER INSERT ON `tb_inputbuku` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET stok=stok+new.jumlah WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_penjualan`
--

CREATE TABLE `tb_penjualan` (
  `kodetransaksi` varchar(50) NOT NULL,
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `tanggal` date NOT NULL,
  `harga` int(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `subtotal` int(50) NOT NULL,
  `total` int(50) NOT NULL,
  `bayar` int(50) NOT NULL,
  `kembalian` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_penjualan`
--

INSERT INTO `tb_penjualan` (`kodetransaksi`, `kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `tanggal`, `harga`, `jumlah`, `subtotal`, `total`, `bayar`, `kembalian`) VALUES
('TR001', 'KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', '2021-04-03', 25000, 2, 50000, 50000, 50000, 0),
('TR002', 'KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', '2021-04-03', 25000, 1, 25000, 25000, 50000, 25000),
('TR003', 'KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', '2021-04-03', 25000, 2, 50000, 50000, 100000, 50000),
('TR004', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-03', 25000, 1, 25000, 50000, 100000, 50000),
('TR004', 'KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', '2021-04-03', 25000, 1, 25000, 50000, 100000, 50000),
('TR005', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-11', 25000, 1, 25000, 25000, 50000, 25000),
('TR006', 'KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', '2021-04-11', 25000, 1, 25000, 25000, 50000, 25000);

--
-- Triggers `tb_penjualan`
--
DELIMITER $$
CREATE TRIGGER `updatestok2` AFTER INSERT ON `tb_penjualan` FOR EACH ROW BEGIN
UPDATE tb_stokbuku SET stok=stok-new.jumlah WHERE kodebuku=new.kodebuku;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_stokbuku`
--

CREATE TABLE `tb_stokbuku` (
  `kodebuku` varchar(50) NOT NULL,
  `namabuku` varchar(50) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahunterbit` varchar(50) NOT NULL,
  `hargabuku` int(50) NOT NULL,
  `stok` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_stokbuku`
--

INSERT INTO `tb_stokbuku` (`kodebuku`, `namabuku`, `pengarang`, `penerbit`, `tahunterbit`, `hargabuku`, `stok`) VALUES
('KB001', 'PEMROGRAMAN', 'ERICK', 'ERICK', '2021', 25000, 7),
('KB002', 'JAVA', 'DIMAS', 'DIMAS', '2021', 25000, 27);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_inputbuku`
--
ALTER TABLE `tb_inputbuku`
  ADD PRIMARY KEY (`kodetransaksi`);

--
-- Indexes for table `tb_stokbuku`
--
ALTER TABLE `tb_stokbuku`
  ADD PRIMARY KEY (`kodebuku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
