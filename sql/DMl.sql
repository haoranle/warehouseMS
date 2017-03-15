INSERT INTO `manager` (`id`, `NAME`, `account`, `PASSWORD`, `phone`, `job`, `entry`) VALUES ('001', '刘德华', '1001', '1121', '110', '', '2017-1-10 17:36:47');
INSERT INTO `manager` (`id`, `NAME`, `account`, `PASSWORD`, `phone`, `job`, `entry`) VALUES ('002', '张学友', '1002', '1121', '120', NULL, '2017-1-10 17:37:08');

INSERT INTO `shelf` (`id`, `number`, `remarks`, `w_id`) VALUES ('S001', '1', NULL, 'W001');
INSERT INTO `shelf` (`id`, `number`, `remarks`, `w_id`) VALUES ('S002', '2', NULL, 'W001');
INSERT INTO `shelf` (`id`, `number`, `remarks`, `w_id`) VALUES ('S003', '3', NULL, 'W001');

INSERT INTO `supplier` (`id`, `NAME`, `phone`, `address`) VALUES ('001', '锤子科技', '110', '北京');
INSERT INTO `supplier` (`id`, `NAME`, `phone`, `address`) VALUES ('002', '虾米', '120', '安康');
INSERT INTO `supplier` (`id`, `NAME`, `phone`, `address`) VALUES ('005', '刘德华自传', '119', '香港');
INSERT INTO `supplier` (`id`, `NAME`, `phone`, `address`) VALUES ('102', '张学友自传', '111', '香港');

INSERT INTO `warein` (`id`, `number`, `intime`, `m_id`) VALUES ('001', '10', '2017-1-10 17:18:38', NULL);

INSERT INTO `wareinlist` (`id`, `amount`, `amountless`, `g_id`, `s_id`, `su_id`, `wi_id`) VALUES ('001', '20', '001', '1121', 'S001', '001', '001');
INSERT INTO `wareinlist` (`id`, `amount`, `amountless`, `g_id`, `s_id`, `su_id`, `wi_id`) VALUES ('002', '20', '001', 'B002', 'S003', '005', '001');


INSERT INTO `wareout` (`id`, `number`, `outtime`, `m_id`) VALUES ('001', '20', '2017-1-10 17:33:18', NULL);
INSERT INTO `wareout` (`id`, `number`, `outtime`, `m_id`) VALUES ('002', '20', '2017-1-10 17:33:31', NULL);
INSERT INTO `wareout` (`id`, `number`, `outtime`, `m_id`) VALUES ('003', '20', '2017-1-10 17:33:41', NULL);

INSERT INTO `wareoutlist` (`id`, `amount`, `g_id`, `wil_id`, `wo_id`) VALUES ('001', '20', 'A002', '001', '002');
INSERT INTO `wareoutlist` (`id`, `amount`, `g_id`, `wil_id`, `wo_id`) VALUES ('002', '20', '1121', '001', '001');
