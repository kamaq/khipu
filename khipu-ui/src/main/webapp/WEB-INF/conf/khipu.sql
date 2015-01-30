-- phpMyAdmin SQL Dump
-- version 4.2.6deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 30-01-2015 a las 17:24:39
-- Versión del servidor: 5.5.41-0ubuntu0.14.10.1
-- Versión de PHP: 5.5.12-2ubuntu4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `khipu`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `USP_UserList`(IN `p_codigo` INT)
BEGIN
   select u.idUser, u.name from user u
   where u.idUser >= p_codigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appparameter`
--

CREATE TABLE IF NOT EXISTS `appparameter` (
  `variable` varchar(80) NOT NULL,
  `value` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `component`
--

CREATE TABLE IF NOT EXISTS `component` (
`idComponent` int(11) NOT NULL,
  `idModule` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `className` varchar(500) NOT NULL,
  `ImageFileName` varchar(500) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Volcado de datos para la tabla `component`
--

INSERT INTO `component` (`idComponent`, `idModule`, `name`, `description`, `className`, `ImageFileName`) VALUES
(4, 6, 'Company', 'Management Company', 'form.CompanyFormFacade', 'Company.png'),
(5, 6, 'Branch', 'Management Branch', 'form.branch.BranchFormFacade', '[Deprecated]'),
(7, 1, 'Module Report ', 'Module Report ', 'report.module.ModuleReportFacade', 'ModuleReport.png'),
(8, 1, 'Modules', 'Module Management', 'form.module.ModuleFormFacade', 'Module.png'),
(9, 1, 'Components', 'Component Management', 'form.component.ComponentFormFacade', 'Component.png'),
(11, 1, 'Functions', 'Function Management', 'form.function.FunctionFormFacade', 'Function.png'),
(20, 1, 'Profiles', 'Profile Management', 'form.profile.ProfileFormFacade', 'Profile.png'),
(21, 1, 'Profile Details', 'Profile Detail Mgmt', 'form.profileDetail.ProfileDetailFormFacade', 'ProfileDetail.png'),
(22, 16, 'People Management', 'People Management', 'form.people.PeopleFormFacade', 'People.png'),
(23, 1, 'Users', 'Users Management', 'form.user.UserFormFacade', 'User.png'),
(24, 1, 'User Profiles', 'UserProfile Management', 'form.userProfile.UserProfileFormFacade', 'UserProfile.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `function`
--

CREATE TABLE IF NOT EXISTS `function` (
  `idComponent` int(11) NOT NULL,
  `idFunction` varchar(30) NOT NULL,
  `name` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `function`
--

INSERT INTO `function` (`idComponent`, `idFunction`, `name`) VALUES
(4, 'ReadOnly', 'ReadOnly'),
(5, 'btnCancel', 'Cancel a record'),
(5, 'btnCreate', 'Create a record'),
(5, 'btnModify', 'Modify a record'),
(5, 'ReadOnly', 'ReadOnly'),
(7, 'btnExecute', 'Execute report'),
(7, 'btnExportToExcel', 'Export to Excel'),
(7, 'btnSendByEmail', 'btnSendByEmail'),
(8, 'btnAddComponent', 'Add a Component'),
(8, 'btnComponents', 'Add components'),
(8, 'btnCreate', 'Create a record'),
(8, 'btnDelete', 'Delete permanently a record '),
(8, 'btnEnable', 'Enable / Disable a record'),
(8, 'btnModify', 'Modify a record'),
(9, 'btnCreate', 'Create a record'),
(9, 'btnDelete', 'Delete a record'),
(9, 'btnEnable', 'Enable / Disable a record'),
(9, 'btnFunctions', 'Add a function'),
(9, 'btnModify', 'Modify a record'),
(11, 'btnCreate', 'Create a record'),
(11, 'btnDelete', 'Delete a record'),
(11, 'btnEnable', 'Enable / Disable a record'),
(11, 'btnModify', 'Modify a record'),
(11, 'btnRefresh', 'btnRefresh'),
(11, 'btnValidate', 'Validar'),
(20, 'btnCreate', 'btnCreate'),
(20, 'btnDelete', 'btnDelete'),
(20, 'btnEnable', 'btnEnable'),
(20, 'btnModify', 'btnModify'),
(21, 'btnCreate', 'btnCreate'),
(21, 'btnDelete', 'btnDelete'),
(21, 'btnEnable', 'btnEnable'),
(21, 'btnModify', 'btnModify'),
(22, 'btnReadOnly', 'btnRead'),
(23, 'btnCreate', 'btnCreate'),
(23, 'btnDelete', 'btnDelete'),
(23, 'btnEnable', 'btnEnable'),
(23, 'btnModify', 'btnModify'),
(24, 'btnCreate', 'btnCreate'),
(24, 'btnDelete', 'btnDelete'),
(24, 'btnEnable', 'btnEnable'),
(24, 'btnModify', 'btnModify');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `module`
--

CREATE TABLE IF NOT EXISTS `module` (
`idModule` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `ImageFileName` varchar(500) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `module`
--

INSERT INTO `module` (`idModule`, `name`, `description`, `ImageFileName`) VALUES
(1, 'Configuration', 'Application Configuration', '[Deprecated]'),
(6, 'Organization', 'Management Organization', '[Deprecated]'),
(16, 'People', 'People Management', '[Deprecated]');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
`idProfile` int(11) NOT NULL,
  `name` varchar(80) NOT NULL,
  `enabled` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `profile`
--

INSERT INTO `profile` (`idProfile`, `name`, `enabled`) VALUES
(1, 'Admin', 1),
(2, 'Guest', 0),
(3, 'Manager', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profiledetail`
--

CREATE TABLE IF NOT EXISTS `profiledetail` (
  `idProfile` int(11) NOT NULL,
  `idComponent` int(11) NOT NULL,
  `idFunction` varchar(30) NOT NULL,
  `enabled` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profiledetail`
--

INSERT INTO `profiledetail` (`idProfile`, `idComponent`, `idFunction`, `enabled`) VALUES
(1, 7, 'btnExecute', 1),
(1, 7, 'btnExportToExcel', 1),
(1, 7, 'btnSendByEmail', 1),
(1, 8, 'btnComponents', 1),
(1, 8, 'btnCreate', 1),
(1, 8, 'btnDelete', 1),
(1, 8, 'btnEnable', 1),
(1, 8, 'btnModify', 1),
(1, 9, 'btnCreate', 1),
(1, 9, 'btnDelete', 1),
(1, 9, 'btnEnable', 1),
(1, 9, 'btnModify', 1),
(1, 11, 'btnCreate', 1),
(1, 11, 'btnDelete', 1),
(1, 11, 'btnEnable', 1),
(1, 11, 'btnModify', 1),
(1, 20, 'btnCreate', 1),
(1, 20, 'btnDelete', 1),
(1, 20, 'btnEnable', 1),
(1, 20, 'btnModify', 1),
(1, 21, 'btnCreate', 1),
(1, 21, 'btnDelete', 1),
(1, 21, 'btnEnable', 0),
(1, 21, 'btnModify', 1),
(1, 23, 'btnCreate', 1),
(1, 23, 'btnDelete', 1),
(1, 23, 'btnEnable', 1),
(1, 23, 'btnModify', 1),
(1, 24, 'btnCreate', 1),
(1, 24, 'btnDelete', 1),
(1, 24, 'btnEnable', 1),
(1, 24, 'btnModify', 1),
(2, 7, 'btnExecute', 1),
(2, 8, 'btnCreate', 1),
(2, 8, 'btnDelete', 1),
(2, 20, 'btnCreate', 1),
(2, 23, 'btnCreate', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`idUser` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `enabled` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`idUser`, `name`, `password`, `email`, `enabled`) VALUES
(1, 'wil', '123', 'wilmer.reyes@outlook.com', 1),
(2, 'forrest', 'runforrestrun', 'wilmer_reyes@hotmail.com', 1),
(3, 'bill', 'carson', 'iwilmer@hotmail.com', 0),
(4, 'bradock', 'bulldog', 'jamesj.bradock@raw.com', 1),
(5, 'chris', 'nielsen', 'chris.nielsen@raw.com', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `userfunction`
--

CREATE TABLE IF NOT EXISTS `userfunction` (
  `idUser` int(11) NOT NULL,
  `idComponent` int(11) NOT NULL,
  `idFunction` varchar(30) NOT NULL,
  `enabled` binary(1) NOT NULL,
  `dateInsert` datetime NOT NULL,
  `userInsert` int(11) NOT NULL,
  `dateUpdate` datetime NOT NULL,
  `userUpdate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `userprofile`
--

CREATE TABLE IF NOT EXISTS `userprofile` (
  `idProfile` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `dateInsert` datetime NOT NULL,
  `userInsert` int(11) NOT NULL,
  `dateUpdate` datetime NOT NULL,
  `userUpdate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `userprofile`
--

INSERT INTO `userprofile` (`idProfile`, `idUser`, `dateInsert`, `userInsert`, `dateUpdate`, `userUpdate`) VALUES
(1, 1, '2014-07-03 00:00:00', 777, '2014-07-03 00:00:00', 233),
(1, 3, '2014-12-10 13:24:28', 1, '2014-12-10 13:24:28', 1),
(2, 2, '2014-09-01 00:00:00', 300, '2014-09-02 00:00:00', 400),
(3, 4, '2014-12-10 14:53:53', 1, '2014-12-10 14:53:53', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `appparameter`
--
ALTER TABLE `appparameter`
 ADD PRIMARY KEY (`variable`);

--
-- Indices de la tabla `component`
--
ALTER TABLE `component`
 ADD PRIMARY KEY (`idComponent`), ADD KEY `module_component_fk` (`idModule`);

--
-- Indices de la tabla `function`
--
ALTER TABLE `function`
 ADD PRIMARY KEY (`idComponent`,`idFunction`);

--
-- Indices de la tabla `module`
--
ALTER TABLE `module`
 ADD PRIMARY KEY (`idModule`);

--
-- Indices de la tabla `profile`
--
ALTER TABLE `profile`
 ADD PRIMARY KEY (`idProfile`);

--
-- Indices de la tabla `profiledetail`
--
ALTER TABLE `profiledetail`
 ADD PRIMARY KEY (`idProfile`,`idComponent`,`idFunction`), ADD KEY `function_profiledetail_fk` (`idComponent`,`idFunction`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`idUser`);

--
-- Indices de la tabla `userfunction`
--
ALTER TABLE `userfunction`
 ADD PRIMARY KEY (`idUser`,`idComponent`,`idFunction`), ADD KEY `function_userfunction_fk` (`idComponent`,`idFunction`);

--
-- Indices de la tabla `userprofile`
--
ALTER TABLE `userprofile`
 ADD PRIMARY KEY (`idProfile`,`idUser`), ADD KEY `user_userprofile_fk` (`idUser`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `component`
--
ALTER TABLE `component`
MODIFY `idComponent` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT de la tabla `module`
--
ALTER TABLE `module`
MODIFY `idModule` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `profile`
--
ALTER TABLE `profile`
MODIFY `idProfile` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `component`
--
ALTER TABLE `component`
ADD CONSTRAINT `module_component_fk` FOREIGN KEY (`idModule`) REFERENCES `module` (`idModule`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `function`
--
ALTER TABLE `function`
ADD CONSTRAINT `component_function_fk` FOREIGN KEY (`idComponent`) REFERENCES `component` (`idComponent`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `profiledetail`
--
ALTER TABLE `profiledetail`
ADD CONSTRAINT `function_profiledetail_fk` FOREIGN KEY (`idComponent`, `idFunction`) REFERENCES `function` (`idComponent`, `idFunction`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `userprofile_userprofiledetail_fk` FOREIGN KEY (`idProfile`) REFERENCES `profile` (`idProfile`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `userfunction`
--
ALTER TABLE `userfunction`
ADD CONSTRAINT `function_userfunction_fk` FOREIGN KEY (`idComponent`, `idFunction`) REFERENCES `function` (`idComponent`, `idFunction`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `user_userfunction_fk` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `userprofile`
--
ALTER TABLE `userprofile`
ADD CONSTRAINT `profile_userprofile_fk` FOREIGN KEY (`idProfile`) REFERENCES `profile` (`idProfile`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `user_userprofile_fk` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
