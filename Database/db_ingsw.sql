-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Feb 12, 2019 alle 09:43
-- Versione del server: 10.1.37-MariaDB
-- Versione PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_ingsw`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `credenziali`
--

CREATE TABLE `credenziali` (
  `nickname` varchar(99) NOT NULL,
  `idUtente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `credenziali`
--

INSERT INTO `credenziali` (`nickname`, `idUtente`) VALUES
('cliente1', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `ingiunzione`
--

CREATE TABLE `ingiunzione` (
  `idIngiunzione` int(11) NOT NULL,
  `importo` double NOT NULL,
  `stato` enum('P','NE','E','C','S') NOT NULL,
  `nProtocollo` varchar(32) DEFAULT NULL,
  `mora` double NOT NULL,
  `mcTotale` double NOT NULL,
  `dataUltimaModifica` date DEFAULT NULL,
  `idUtenza` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `ingiunzione`
--

INSERT INTO `ingiunzione` (`idIngiunzione`, `importo`, `stato`, `nProtocollo`, `mora`, `mcTotale`, `dataUltimaModifica`, `idUtenza`) VALUES
(134, 2192.536, 'C', NULL, 0, 2458, '2017-09-04', 7),
(135, 2676, 'E', 'GCI-6-135', 0, 3000, '2017-09-04', 6),
(140, 2330, 'E', 'GCI-5-140', 100, 2500, '2017-09-30', 5),
(141, 2232.676, 'E', 'GCI-8-141', 0, 2503, '2017-09-30', 8),
(142, 4085.36, 'NE', NULL, 0, 4580, '2017-09-30', 9);

--
-- Trigger `ingiunzione`
--
DELIMITER $$
CREATE TRIGGER `aggiornaData` BEFORE UPDATE ON `ingiunzione` FOR EACH ROW SET NEW.dataUltimaModifica=CURRENT_DATE()
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `associaIngiunzione` AFTER INSERT ON `ingiunzione` FOR EACH ROW BEGIN
UPDATE utenza SET utenza.idIngiunzione = NEW.idIngiunzione WHERE utenza.idUtenza = NEW.idUtenza; 
UPDATE lettura SET lettura.idIngiunzione = NEW.idIngiunzione WHERE lettura.idUtenza = NEW.idUtenza;


END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `lettura`
--

CREATE TABLE `lettura` (
  `idLettura` int(11) NOT NULL,
  `mc` double NOT NULL,
  `dataLettura` date NOT NULL,
  `statoBolletta` enum('P','NP','NV') NOT NULL,
  `idIngiunzione` int(11) DEFAULT NULL,
  `idUtenza` int(11) NOT NULL,
  `effettuataDa` enum('T','C') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `lettura`
--

INSERT INTO `lettura` (`idLettura`, `mc`, `dataLettura`, `statoBolletta`, `idIngiunzione`, `idUtenza`, `effettuataDa`) VALUES
(10, 1245, '2017-08-09', 'NP', 141, 8, 'T'),
(12, 2458, '2017-08-02', 'NP', 134, 7, 'T'),
(13, 1245, '2017-09-04', 'NP', 135, 6, 'T'),
(14, 4580, '2017-09-05', 'NP', 142, 9, 'T'),
(16, 458, '2017-09-01', 'NP', 140, 5, 'T'),
(17, 258, '2017-09-14', 'NP', 140, 5, 'T'),
(18, 850, '2017-09-15', 'NP', 140, 5, 'T'),
(19, 285, '2017-09-23', 'NP', 140, 5, 'T'),
(20, 985, '2017-05-15', 'NP', 140, 5, 'T'),
(21, 1258, '2017-09-21', 'NP', 141, 8, 'T'),
(1000, 2980, '2017-08-15', 'NP', NULL, 5, 'T');

-- --------------------------------------------------------

--
-- Struttura della tabella `utenza`
--

CREATE TABLE `utenza` (
  `idUtenza` int(11) NOT NULL,
  `codContatore` int(11) NOT NULL,
  `nome` varchar(32) NOT NULL,
  `cognome` varchar(32) NOT NULL,
  `indirizzo` varchar(64) NOT NULL,
  `citta` varchar(32) NOT NULL,
  `cF` varchar(32) NOT NULL,
  `dataNascita` date NOT NULL,
  `idIngiunzione` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `utenza`
--

INSERT INTO `utenza` (`idUtenza`, `codContatore`, `nome`, `cognome`, `indirizzo`, `citta`, `cF`, `dataNascita`, `idIngiunzione`) VALUES
(5, 1001, 'Gianluca', 'Sabella', 'Via de Morgen 74', 'Napoli', 'SBLGL93LNC11OPR', '1992-08-14', 140),
(6, 1002, 'Luca', 'De Vincenzo', 'Via della croce', 'Napoli', 'DVCLVC981DRO25', '1974-09-21', 135),
(7, 2587, 'Domenico', 'Tarallo', 'Via dei Colli 58', 'Napoli', 'DMTRALL92SDRI', '1954-11-08', 134),
(8, 110, 'Dario', 'Palmieri', 'Via del mare', 'Napoli', 'PLMDRA91RSS', '1991-10-11', 141),
(9, 980, 'Lello', 'Lelli', 'Via dei mille', 'Napoli', 'LLDMCK987DKEE', '1970-01-05', 142),
(10, 784, 'Piergiorgio', 'Altobelli', 'Via sotto la collina 187', 'Bologna', 'PGRBHSB875GRIRHFT', '1980-03-04', NULL),
(11, 777, 'Serena', 'Salatiello', 'Via Ripuaria', 'Napoli', 'SSERJENDJUTIOFKNH', '1990-10-10', NULL),
(12, 651, 'Ernesto', 'Locomotiva', 'Via Giacomo Leopardi', 'Milano', 'ERNURHNDBTHJRKTNU', '1949-02-25', NULL),
(13, 951, 'Filippo', 'Visone', 'Via Manzoni 545', 'Roma', 'FLPVSN894OIEYRH45', '1975-12-13', NULL),
(14, 394, 'Domenico', 'Taffuri', 'Via caserta 54', 'Caserta', 'DRMNTHF87945OITHRB', '1980-06-15', NULL);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `credenziali`
--
ALTER TABLE `credenziali`
  ADD KEY `esterna` (`idUtente`);

--
-- Indici per le tabelle `ingiunzione`
--
ALTER TABLE `ingiunzione`
  ADD PRIMARY KEY (`idIngiunzione`),
  ADD UNIQUE KEY `idIngiunzione` (`idIngiunzione`),
  ADD UNIQUE KEY `idUtenza` (`idUtenza`);

--
-- Indici per le tabelle `lettura`
--
ALTER TABLE `lettura`
  ADD PRIMARY KEY (`idLettura`),
  ADD KEY `idUtenza` (`idUtenza`),
  ADD KEY `lettua_ibfk_2` (`idIngiunzione`);

--
-- Indici per le tabelle `utenza`
--
ALTER TABLE `utenza`
  ADD PRIMARY KEY (`idUtenza`),
  ADD UNIQUE KEY `codContatore` (`codContatore`),
  ADD UNIQUE KEY `idIngiunzione` (`idIngiunzione`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `ingiunzione`
--
ALTER TABLE `ingiunzione`
  MODIFY `idIngiunzione` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT per la tabella `lettura`
--
ALTER TABLE `lettura`
  MODIFY `idLettura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1241;

--
-- AUTO_INCREMENT per la tabella `utenza`
--
ALTER TABLE `utenza`
  MODIFY `idUtenza` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `credenziali`
--
ALTER TABLE `credenziali`
  ADD CONSTRAINT `esterna` FOREIGN KEY (`idUtente`) REFERENCES `utenza` (`idUtenza`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `ingiunzione`
--
ALTER TABLE `ingiunzione`
  ADD CONSTRAINT `ingiunzione_ibfk_1` FOREIGN KEY (`idUtenza`) REFERENCES `utenza` (`idUtenza`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `lettura`
--
ALTER TABLE `lettura`
  ADD CONSTRAINT `lettua_ibfk_2` FOREIGN KEY (`idIngiunzione`) REFERENCES `ingiunzione` (`idIngiunzione`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `lettura_ibfk_1` FOREIGN KEY (`idUtenza`) REFERENCES `utenza` (`idUtenza`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `utenza`
--
ALTER TABLE `utenza`
  ADD CONSTRAINT `utenza_ibfk_1` FOREIGN KEY (`idIngiunzione`) REFERENCES `ingiunzione` (`idIngiunzione`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
