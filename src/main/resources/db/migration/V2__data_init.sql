INSERT INTO public.users (id, date_created, last_modified, name, password_hash, surname, user_name) VALUES (1, '2022-01-10 10:37:21.037000', '2022-01-10 10:37:21.037000', 'one', '1', 'one', 'one');
INSERT INTO public.users (id, date_created, last_modified, name, password_hash, surname, user_name) VALUES (2, '2022-01-10 10:37:32.325000', '2022-01-10 10:37:32.325000', 'two', '1', 'two', 'two');

INSERT INTO public.estate (id, area, bathrooms, bathrooms_area, date_created, floor_area, is_common_bathroom_with_toilet, kitchen_area, last_modified, rooms, toilets, toilets_area, user_id) VALUES (6, 100, 1, 10, '2022-01-10 10:47:01.376000', 500, true, 20, '2022-01-10 10:47:01.376000', 4, 1, 5, 1);
INSERT INTO public.estate (id, area, bathrooms, bathrooms_area, date_created, floor_area, is_common_bathroom_with_toilet, kitchen_area, last_modified, rooms, toilets, toilets_area, user_id) VALUES (7, 50, 1, 10, '2022-01-10 10:47:12.347000', 500, true, 20, '2022-01-10 10:47:12.347000', 4, 1, 5, 1);
INSERT INTO public.estate (id, area, bathrooms, bathrooms_area, date_created, floor_area, is_common_bathroom_with_toilet, kitchen_area, last_modified, rooms, toilets, toilets_area, user_id) VALUES (8, 50, 1, 10, '2022-01-10 10:47:17.450000', 500, true, 20, '2022-01-10 10:47:17.450000', 4, 1, 5, 2);
INSERT INTO public.estate (id, area, bathrooms, bathrooms_area, date_created, floor_area, is_common_bathroom_with_toilet, kitchen_area, last_modified, rooms, toilets, toilets_area, user_id) VALUES (9, 100, 1, 10, '2022-01-10 10:47:24.436000', 500, true, 20, '2022-01-10 10:47:24.436000', 4, 1, 5, 2);

INSERT INTO public.team (id, name) VALUES (3, 'team1');
INSERT INTO public.team (id, name) VALUES (5, 'team3');
INSERT INTO public.team (id, name) VALUES (4, 'team2');

INSERT INTO public.job_category (id, name) VALUES (35, 'стяжка');

INSERT INTO public.trade_mark (id, name) VALUES (12, 'cimsa');
INSERT INTO public.trade_mark (id, name) VALUES (16, 'cerezit');
INSERT INTO public.trade_mark (id, name) VALUES (20, 'baugut');
INSERT INTO public.trade_mark (id, name) VALUES (25, 'simba');

INSERT INTO public.material_category (id, name) VALUES (11, 'cement');
INSERT INTO public.material_category (id, name) VALUES (24, 'pesok');

INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (15, 'string', 'cement4', 15, 2, 25, 375, 'HIGH', 11, 16);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (14, 'string', 'cement3', 12, 2, 25, 300, 'MIDDLE', 11, 12);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (13, 'string', 'cement2', 10, 2, 25, 250, 'MIDDLE', 11, 12);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (10, 'string', 'cement1', 8, 2, 25, 200, 'MIDDLE', 11, 12);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (17, 'string', 'cement5', 20, 2, 25, 500, 'HIGH', 11, 16);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (18, 'string', 'cement6', 25, 2, 25, 625, 'HIGH', 11, 16);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (19, 'string', 'cement7', 3, 2, 25, 75, 'LOW', 11, 20);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (21, 'string', 'cement8', 4, 2, 25, 100, 'LOW', 11, 20);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (22, 'string', 'cement9', 5, 2, 25, 125, 'LOW', 11, 20);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (23, 'string', 'pesok1', 1, 2, 25, 25, 'LOW', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (26, 'string', 'pesok2', 2, 2, 25, 50, 'LOW', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (27, 'string', 'pesok3', 3, 2, 25, 75, 'LOW', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (28, 'string', 'pesok4', 4, 2, 25, 100, 'MIDDLE', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (29, 'string', 'pesok5', 5, 2, 25, 125, 'MIDDLE', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (30, 'string', 'pesok6', 6, 2, 25, 150, 'MIDDLE', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (31, 'string', 'pesok8', 8, 2, 25, 200, 'MIDDLE', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (32, 'string', 'pesok9', 9, 2, 25, 225, 'HIGH', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (33, 'string', 'pesok10', 10, 2, 25, 250, 'HIGH', 24, 25);
INSERT INTO public.material (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, price_level, material_category_id, trade_mark_id) VALUES (34, 'string', 'pesok11', 11, 2, 25, 275, 'HIGH', 24, 25);

INSERT INTO public.materials_formula (id, name) VALUES (36, 'm400');

INSERT INTO public.job_category_required_materials_formula (job_category_id, materials_formula_id) VALUES (35, 36);

INSERT INTO public.job (id, description, name, cost, measure_unit, quantity_in_one_unit, total_cost, category_id) VALUES (39, 'string', 'стяжка', 100, 1, 0, 0, 35);

INSERT INTO public.materials_formula_proportions (id, quantity_in_one_measure_unit, ratio, material_category_id) VALUES (37, 88.25, 0.22, 11);
INSERT INTO public.materials_formula_proportions (id, quantity_in_one_measure_unit, ratio, material_category_id) VALUES (38, 88.25, 0.88, 24);

INSERT INTO public.materials_formula_material_category_proportions (materials_formula_id, material_category_proportions_id) VALUES (36, 37);
INSERT INTO public.materials_formula_material_category_proportions (materials_formula_id, material_category_proportions_id) VALUES (36, 38);
