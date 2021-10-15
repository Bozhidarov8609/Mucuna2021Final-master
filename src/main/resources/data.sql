# -- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts
#
#
# -- dogsEntity
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (1,10,true,'GOLDEN_RETRIEVER','Casper','http://res.cloudinary.com/bozhidar/image/upload/v1634114763/px9jdvbyh0l3p9yam9md.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (2,8,true,'SPANISH_WATER_DOG','Spanish','https://res.cloudinary.com/bozhidar/image/upload/v1620706699/h1hszpziyn41bumype5d.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (3,5,true,'BOLOGNESE','Topsy','https://res.cloudinary.com/bozhidar/image/upload/v1634114079/o6b7wgbejxror6q2ogjt.jpg','female',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (4,13,true,'GERMAN_SHEPHERD_DOG','Arak','https://res.cloudinary.com/bozhidar/image/upload/v1620714656/r7bjmqxnnm0xybn5iwzs.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (5,6,true,'BRIARD','Bri','https://res.cloudinary.com/bozhidar/image/upload/v1617450402/bhokqxr9ifzxtzfkeoya.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (6,6,true,'DALMATIAN','Delmi','https://res.cloudinary.com/bozhidar/image/upload/v1617442446/ppncxqijkehxwn9q3j0p.jpg','female',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (7,5,true,'ROTTWEILER','King','https://res.cloudinary.com/bozhidar/image/upload/v1617450105/lefkbrxvxj9ponuchngq.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (8,8,true,'HYGEN_HOUND','Dogi','https://res.cloudinary.com/bozhidar/image/upload/v1617443645/mfncvdifar0tzxmex4gf.jpg','female',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (9,6,true,'LABRADUDEL','Lapi','https://res.cloudinary.com/bozhidar/image/upload/v1618128568/zogbjlwr5srjweslnnjo.jpg','male',1);
#
# INSERT mucuna072021.dogs (id, age,approved,breed,name,picture,sex,user_entity_id)
# VALUES (10,3,false,'LABRADUDEL','Ugly','https://res.cloudinary.com/bozhidar/image/upload/v1618128509/aciehofyggxmyqv5tfdq.jpg','male',1);

# INSERT mucuna072021.events(id, name, picture, place, price, user_id)
# VALUES (1,'New Dog Playground','https://res.cloudinary.com/bozhidar/image/upload/v1626919730/fb0rquwkotxelolrcs4e.jpg','Varna',0,2)

# INSERT mucuna072021.events(id, name, picture, place, price, user_id)
# VALUES (2,'MedicalExamination','http://res.cloudinary.com/bozhidar/image/upload/v1634226392/if63ep4uieidxnqitgcq.jpg','Varna,ul.Drin 59',0,1)
#
INSERT mucuna072021.puppies(id, contact, description, name, picture, price, dog_id)
VALUES(1,'0883300389','proven origin','popy','https://res.cloudinary.com/bozhidar/image/upload/v1620715759/u8hjpcq4o8lc3trg7s4u.jpg','300',1)

