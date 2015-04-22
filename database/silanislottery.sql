-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 22 Avril 2015 à 14:46
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `silanislottery`
--
CREATE DATABASE IF NOT EXISTS `silanislottery` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `silanislottery`;

-- --------------------------------------------------------

--
-- Structure de la table `draw`
--

CREATE TABLE IF NOT EXISTS `draw` (
  `id_draw` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` datetime DEFAULT NULL,
  `draw_date` datetime NOT NULL,
  `is_open` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_draw`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Contenu de la table `draw`
--

INSERT INTO `draw` (`id_draw`, `creation_date`, `draw_date`, `is_open`) VALUES
(3, '2015-04-01 00:00:00', '2015-04-18 00:00:00', 0),
(13, '2015-04-16 00:00:00', '2015-04-11 00:00:00', 1),
(14, '2015-04-16 00:00:00', '2015-04-20 00:00:00', 1),
(15, '2015-04-20 19:02:57', '2015-04-08 00:00:00', 1),
(16, '2015-04-21 09:53:43', '2017-06-04 00:00:00', 1),
(17, '2015-04-21 09:53:53', '2017-01-04 00:00:00', 1);

-- --------------------------------------------------------

--
-- Structure de la table `ticket`
--

CREATE TABLE IF NOT EXISTS `ticket` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `participant_name` varchar(100) NOT NULL,
  `inscription_date` datetime DEFAULT NULL,
  `price` int(11) NOT NULL DEFAULT '10',
  `id_draw` int(11) NOT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `fk_TICKET_DRAW1_idx` (`id_draw`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Contenu de la table `ticket`
--

INSERT INTO `ticket` (`id_ticket`, `participant_name`, `inscription_date`, `price`, `id_draw`) VALUES
(11, 'toto', '2015-04-20 00:00:00', 10, 3),
(12, 'jaquy', '2015-04-20 00:00:00', 10, 3),
(13, 'tutu', '2015-04-20 00:00:00', 10, 3),
(14, 'jean jaque', '2015-04-20 00:00:00', 10, 13),
(15, 'romain vincent', '2015-04-21 00:00:00', 10, 13);

-- --------------------------------------------------------

--
-- Structure de la table `winner`
--

CREATE TABLE IF NOT EXISTS `winner` (
  `id_ticket` int(11) NOT NULL,
  `id_draw` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id_ticket`,`id_draw`),
  KEY `fk_WINNER_DRAW1_idx` (`id_draw`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `winner`
--

INSERT INTO `winner` (`id_ticket`, `id_draw`, `position`, `amount`) VALUES
(11, 3, 3, 1),
(12, 3, 2, 2),
(13, 3, 1, 11);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `fk_TICKET_DRAW1` FOREIGN KEY (`id_draw`) REFERENCES `draw` (`id_draw`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `winner`
--
ALTER TABLE `winner`
  ADD CONSTRAINT `fk_TICKET_DRAW` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_WINNER_DRAW1` FOREIGN KEY (`id_draw`) REFERENCES `draw` (`id_draw`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
