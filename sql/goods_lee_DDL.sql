
create table power
(
   p_id varchar(50) not null,
   p_level              varchar(100),
   p_out                varchar(100),
   p_in                 varchar(100),
   p_goods_up           varchar(100),
   p_mang_up            varchar(100),
   primary key (p_id)
);

create table supplier
(
   s_id                 varchar(50) not null,
   s_name               varchar(100),
   s_city               varchar(100),
   primary key (s_id)
);

create table goodsType
(
   t_id                 varchar(50) not null,
   t_name               varchar(100),
   primary key (t_id)
);

create table manager
(
   m_id                 varchar(50) not null,
   p_id                 varchar(50),
   m_name               varchar(100),
   m_password           varchar(100),
   primary key (m_id)
);

alter table manager add constraint FK_Reference_8 foreign key (p_id)
      references power (p_id) on delete restrict on update restrict;

create table goods
(
   g_id                 varchar(50) not null,
   t_id                 varchar(50),
   s_id                 varchar(50),
   g_name               varchar(100),
   g_brand              varchar(100),
   g_desc               varchar(100),
   primary key (g_id)
);

alter table goods add constraint FK_Reference_6 foreign key (t_id)
      references goodsType (t_id) on delete restrict on update restrict;

alter table goods add constraint FK_Reference_7 foreign key (s_id)
      references supplier (s_id) on delete restrict on update restrict;


create table batch
(
   b_id                 varchar(50) not null,
   g_id                 varchar(50),
   m_id                 varchar(50),
   b_date               datetime,
   b_type               varchar(50),
   primary key (b_id)
);

alter table batch add constraint FK_Reference_10 foreign key (m_id)
      references manager (m_id) on delete restrict on update restrict;

alter table batch add constraint FK_Reference_9 foreign key (g_id)
      references goods (g_id) on delete restrict on update restrict;



