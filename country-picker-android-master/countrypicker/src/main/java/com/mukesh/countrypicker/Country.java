package com.mukesh.countrypicker;

import android.content.Context;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by mukesh on 25/04/16.
 * Edit by Orlov Aleksandr on 20/10/17
 */

public class Country {
  public static final Country[] COUNTRIES = {
//      new Country("AD", "Andorra", "+376", R.drawable.flag_ad),
      new Country("AE", "United Arab Emirates", "ОАЭ", R.drawable.flag_ae, 77.1f, 78.6f, 76.4f),
      new Country("AF", "Afghanistan", "Афганистан", R.drawable.flag_af, 60.5f, 61.9f, 59.3f),
      new Country("AG", "Antigua and Barbuda", "Антигуа и Барбуда", R.drawable.flag_ag, 76.4f, 78.6f, 74.1f),
//      new Country("AI", "Anguilla", "+1", R.drawable.flag_ai),
      new Country("AL", "Albania", "Албания", R.drawable.flag_al, 77.8f, 80.7f, 75.1f),
      new Country("AM", "Armenia", "Армения", R.drawable.flag_am, 74.8f, 77.7f, 71.6f),
      new Country("AO", "Angola", "Ангола", R.drawable.flag_ao, 52.4f, 54f, 50.9f),
//      new Country("AQ", "Antarctica", "+672", R.drawable.flag_aq),
      new Country("AR", "Argentina", "Аргентина", R.drawable.flag_ar, 76.3f, 79.9f, 72.7f),
//      new Country("AS", "AmericanSamoa", "+1", R.drawable.flag_as),
      new Country("AT", "Austria", "Австрия", R.drawable.flag_at, 81.5f, 83.9f, 79f),
      new Country("AU", "Australia", "Австралия", R.drawable.flag_au, 82.8f, 84.8f, 80.9f),
//      new Country("AW", "Aruba", "+297", R.drawable.flag_aw),
//      new Country("AX", "Åland Islands", "+358", R.drawable.flag_ax),
      new Country("AZ", "Azerbaijan", "Азербайджан", R.drawable.flag_az, 72.7f, 75.8f, 69.6f),
      new Country("BA", "Bosnia and Herzegovina", "Босния и Герцеговина", R.drawable.flag_ba, 77.4f, 79.7f, 75f),
      new Country("BB", "Barbados", "Барбадос", R.drawable.flag_bb, 75.5f, 77.9f, 73.1f),
      new Country("BD", "Bangladesh", "Бангладеш", R.drawable.flag_bd, 71.8f, 73.1f, 70.6f),
      new Country("BE", "Belgium", "Бельгия", R.drawable.flag_be, 81.1f, 83.5f, 78.6f),
      new Country("BF", "Burkina Faso", "Буркина Фасо", R.drawable.flag_bf, 59.9f, 60.5f, 59.1f),
      new Country("BG", "Bulgaria", "Болгария", R.drawable.flag_bg, 74.5f, 78f, 71.1f),
      new Country("BH", "Bahrain", "Бахрейн", R.drawable.flag_bh, 76.9f, 77.9f, 76.2f),
      new Country("BI", "Burundi", "Бурунди", R.drawable.flag_bi, 59.6f, 61.6f, 57.7f),
      new Country("BJ", "Benin", "Бенин", R.drawable.flag_bj, 60f, 61.1f, 58.8f),
//      new Country("BL", "Saint Barthélemy", "+590", R.drawable.flag_bl),
//      new Country("BM", "Bermuda", "+1", R.drawable.flag_bm),
      new Country("BN", "Brunei", "Бруней", R.drawable.flag_bn, 77.7f, 79.2f, 76.3f),
      new Country("BO", "Bolivia", "Боливия", R.drawable.flag_bo, 70.7f, 73.3f, 68.2f),
//      new Country("BQ", "Bonaire", "+599", R.drawable.flag_bq),
      new Country("BR", "Brazil", "Бразилия", R.drawable.flag_br, 76.1f, 79.7f, 72.5f),
      new Country("BS", "Bahamas", "Багамы", R.drawable.flag_bs, 76.1f, 79.1f, 72.9f),
      new Country("BT", "Bhutan", "Бутан", R.drawable.flag_bt, 69.8f, 70.1f, 69.5f),
//      new Country("BV", "Bouvet Island", "+47", R.drawable.flag_bv),
      new Country("BW", "Botswana", "Ботсвана", R.drawable.flag_bw, 65.7f, 68.1f, 63.3f),
      new Country("BY", "Belarus", "Беларусь", R.drawable.flag_by, 72.3f, 78f, 66.5f),
      new Country("BZ", "Belize", "Белиз", R.drawable.flag_bz, 70.1f, 73.1f, 67.5f),
      new Country("CA", "Canada", "Канада", R.drawable.flag_ca, 82.2f, 84.1f, 80.2f),
//      new Country("CC", "Cocos (Keeling) Islands", "+61", R.drawable.flag_cc),
      new Country("CD", "DR Congo", "Демократическая Республика Конго", R.drawable.flag_cd, 59.8f, 61.5f, 58.3f),
      new Country("CF", "Central African Republic", "Центральноафриканская Республика", R.drawable.flag_cf, 52.5f, 54.1f, 50.9f),
      new Country("CG", "Congo", "Конго", R.drawable.flag_cg, 64.7f, 66.3f, 63.2f),
      new Country("CH", "Switzerland", "Швейцария", R.drawable.flag_ch, 83.4f, 85.3f, 81.3f),
      new Country("CI", "Ivory Coast", "Кот-д’Ивуар", R.drawable.flag_ci, 53.3f, 54.4f, 52.3f),
//      new Country("CK", "Cook Islands", "+682", R.drawable.flag_ck),
      new Country("CL", "Chile", "Чили", R.drawable.flag_cl, 80.5f, 83.4f, 77.4f),
      new Country("CM", "Cameroon", "Камерун", R.drawable.flag_cm, 57.3f, 58.6f, 55.9f),
      new Country("CN", "China", "Китай", R.drawable.flag_cn, 76.1f, 77.6f, 74.6f),
      new Country("CO", "Colombia", "Колумбия", R.drawable.flag_co, 74.8f, 78.4f, 71.2f),
      new Country("CR", "Costa Rica", "Коста Рика", R.drawable.flag_cr, 79.6f, 82.2f, 77.1f),
      new Country("CU", "Куба", "+53", R.drawable.flag_cu, 79.1f, 81.4f, 76.9f),
      new Country("CV", "Cape Verde", "Кабо-Верде", R.drawable.flag_cv, 73.3f, 75f, 71.3f),
//      new Country("CW", "Curacao", "+599", R.drawable.flag_cw),
//      new Country("CX", "Christmas Island", "+61", R.drawable.flag_cx),
      new Country("CY", "Cyprus", "Кипр", R.drawable.flag_cy, 80.5f, 82.7f, 78.3f),
      new Country("CZ", "Czech Republic", "Чехия", R.drawable.flag_cz, 78.8f, 81.7f, 75.9f),
      new Country("DE", "Germany", "Германия", R.drawable.flag_de, 81f, 83.4f, 78.7f),
      new Country("DJ", "Djibouti", "Джибути", R.drawable.flag_dj, 63.5f, 65.3f, 61.8f),
      new Country("DK", "Denmark", "Дания", R.drawable.flag_dk, 80.6f, 82.5f, 78.6f),
//      new Country("DM", "Dominica", "+1", R.drawable.flag_dm),
      new Country("DO", "Dominican Republic", "Доминиканская Республика", R.drawable.flag_do, 73.9f, 77.1f, 70.9f),
      new Country("DZ", "Algeria", "Алжир", R.drawable.flag_dz, 75.6f, 77.5f, 73.8f),
      new Country("EC", "Ecuador", "Эквадор", R.drawable.flag_ec, 76.2f, 79f, 73.5f),
      new Country("EE", "Estonia", "Естония", R.drawable.flag_ee, 77.7f, 82f, 73.1f),
      new Country("EG", "Egypt", "Египет", R.drawable.flag_eg, 70.9f, 73.2f, 68.8f),
//      new Country("EH", "Western Sahara", "+212", R.drawable.flag_eh),
      new Country("ER", "Eritrea", "Эритрея", R.drawable.flag_er, 64.7f, 67f, 62.4f),
      new Country("ES", "Spain", "Испания", R.drawable.flag_es, 82.8f, 85.5f, 80.1f),
      new Country("ET", "Ethiopia", "Эфиопия", R.drawable.flag_et, 64.8f, 66.8f, 62.8f),
      new Country("FI", "Finland", "Финляндия", R.drawable.flag_fi, 81.1f, 83.8f, 78.3f),
      new Country("FJ", "Fiji", "Фиджи", R.drawable.flag_fj, 69.9f, 73.1f, 67f),
//      new Country("FK", "Falkland Islands (Malvinas)", "+500", R.drawable.flag_fk),
      new Country("FM", "Micronesia", "Микронезия", R.drawable.flag_fm, 69.4f, 70.6f, 68.1f),
//      new Country("FO", "Faroe Islands", "+298", R.drawable.flag_fo),
      new Country("FR", "France", "Франция", R.drawable.flag_fr, 82.4f, 85.4f, 79.4f),
      new Country("GA", "Gabon", "Габон", R.drawable.flag_ga, 66f, 67.2f, 64.7f),
        new Country("GB", "United Kingdom", "Великобритания", R.drawable.flag_gb, 81.2f, 83f, 79.4f),
      new Country("GD", "Grenada", "Гренада", R.drawable.flag_gd, 73.6f, 76.1f, 71.2f),
      new Country("GE", "Georgia", "Грузия", R.drawable.flag_ge, 74.4f, 78.3f, 70.3f),
//      new Country("GF", "French Guiana", "+594", R.drawable.flag_gf),
//      new Country("GG", "Guernsey", "+44", R.drawable.flag_gg),
      new Country("GH", "Ghana", "Гана", R.drawable.flag_gh, 62.4f, 63.9f, 61f),
//      new Country("GI", "Gibraltar", "+350", R.drawable.flag_gi),
//      new Country("GL", "Greenland", "+299", R.drawable.flag_gl),
        new Country("GM", "Гамбия", "+220", R.drawable.flag_gm, 61.1f, 62.5f, 59.8f),
      new Country("GN", "Guinea", "Гвинея", R.drawable.flag_gn, 59f, 59.8f, 58.2f),
//      new Country("GP", "Guadeloupe", "+590", R.drawable.flag_gp),
      new Country("GQ", "Equatorial Guinea", "Экваториальная Гвинея", R.drawable.flag_gq, 58.2f, 60f, 56.6f),
      new Country("GR", "Greece", "Греция", R.drawable.flag_gr, 81f, 83.6f, 78.3f),
//      new Country("GS", "South Georgia and the South Sandwich Islands", "+500", R.drawable.flag_gs),
      new Country("GT", "Guatemala", "Гватемала", R.drawable.flag_gt, 71.9f, 75.2f, 68.5f),
//      new Country("GU", "Guam", "+1", R.drawable.flag_gu),
      new Country("GW", "Guinea-Bissau", "Гвинея-Бисау", R.drawable.flag_gw, 58.9f, 60.5f, 57.2f),
      new Country("GY", "Guyana", "Гайана", R.drawable.flag_gy, 66.2f, 68.5f, 63.9f),
//      new Country("HK", "Hong Kong", "+852", R.drawable.flag_hk),
//      new Country("HM", "Heard Island and McDonald Islands", "", R.drawable.flag_hm),
      new Country("HN", "Honduras", "Гондурас", R.drawable.flag_hn, 74.6f, 77f, 72.3f),
      new Country("HR", "Croatia", "Хорватия", R.drawable.flag_hr, 78f, 81.2f, 74.7f),
      new Country("HT", "Haiti", "Гаити", R.drawable.flag_ht, 63.5f, 65.5f, 61.5f),
      new Country("HU", "Hungary", "Венгрия", R.drawable.flag_hu, 75.9f, 79.1f, 72.3f),
      new Country("ID", "Indonesia", "Индонезия", R.drawable.flag_id, 69.1f, 71.2f, 67.1f),
      new Country("IE", "Ireland", "Ирландия", R.drawable.flag_ie, 81.4f, 83.4f, 79.4f),
      new Country("IL", "Israel", "Израиль", R.drawable.flag_il, 82.5f, 84.3f, 80.6f),
//      new Country("IM", "Isle of Man", "+44", R.drawable.flag_im),
      new Country("IN", "India", "Индия", R.drawable.flag_in, 68.3f, 69.9f, 66.9f),
//      new Country("IO", "British Indian Ocean Territory", "+246", R.drawable.flag_io),
      new Country("IQ", "Iraq", "Ирак", R.drawable.flag_iq, 68.9f, 71.8f, 66.2f),
      new Country("IR", "Iran", "Иран", R.drawable.flag_ir, 75.5f, 76.6f, 74.5f),
      new Country("IS", "Iceland", "Исландия", R.drawable.flag_is, 82.7f, 84.1f, 81.2f),
      new Country("IT", "Italy", "Италия", R.drawable.flag_it, 82.7f, 84.8f, 80.5f),
//      new Country("JE", "Jersey", "+44", R.drawable.flag_je),
      new Country("JM", "Jamaica", "Ямайка", R.drawable.flag_jm, 76.2f, 78.6f, 73.9f),
      new Country("JO", "Jordan", "Иордания", R.drawable.flag_jo, 74.1f, 75.9f, 72.5f),
      new Country("JP", "Japan", "Япония", R.drawable.flag_jp, 83.7f, 86.8f, 80.5f),
      new Country("KE", "Kenya", "Кения", R.drawable.flag_ke, 63.4f, 65.8f, 61.1f),
      new Country("KG", "Kyrgyzstan", "Кыргыстан", R.drawable.flag_kg, 71.1f, 75.1f, 67.2f),
      new Country("KH", "Cambodia", "Камбоджа", R.drawable.flag_kh, 68.7f, 70.7f, 66.6f),
      new Country("KI", "Kiribati", "Кирибати", R.drawable.flag_ki, 66.3f, 68.8f, 63.7f),
      new Country("KM", "Comoros", "Коморы", R.drawable.flag_km, 63.5f, 65.2f, 61.9f),
//      new Country("KN", "Saint Kitts and Nevis", "+1", R.drawable.flag_kn),
      new Country("KP", "North Korea", "Северная Корея", R.drawable.flag_kp, 70.6f, 74f, 67f),
      new Country("KR", "South Korea", "Южная Корея", R.drawable.flag_kr, 82.3f, 84.1f, 80.2f),
      new Country("KW", "Kuwait", "Кувейт", R.drawable.flag_kw, 74.7f, 76f, 73.7f),
//      new Country("KY", "Cayman Islands", "+345", R.drawable.flag_ky),
      new Country("KZ", "Kazakhstan", "Казахстан", R.drawable.flag_kz, 70.2f, 74.7f, 65.7f),
      new Country("LA", "Laos", "Лаос", R.drawable.flag_la, 65.7f, 67.2f, 64.1f),
      new Country("LB", "Lebanon", "Ливан", R.drawable.flag_lb, 74.9f, 76.5f, 73.5f),
      new Country("LC", "Saint Lucia", "Сент-Люсия", R.drawable.flag_lc, 75.2f, 77.9f, 72.6f),
//      new Country("LI", "Liechtenstein", "+423", R.drawable.flag_li),
      new Country("LK", "Sri Lanka", "Шри-Ланка", R.drawable.flag_lk, 74.9f, 78.3f, 71.6f),
      new Country("LR", "Liberia", "Либерия", R.drawable.flag_lr, 61.4f, 62.9f, 59.8f),
      new Country("LS", "Lesotho", "Лесото", R.drawable.flag_ls, 53.7f, 55.4f, 51.7f),
      new Country("LT", "Lithuania", "Литва", R.drawable.flag_lt, 73.6f, 79.1f, 68.1f),
      new Country("LU", "Luxembourg", "Люксембург", R.drawable.flag_lu, 82f, 84f, 79.8f),
      new Country("LV", "Latvia", "Латвия", R.drawable.flag_lv, 74.6f, 79.2f, 69.9f),
      new Country("LY", "Libya", "Ливия", R.drawable.flag_ly, 72.9f, 75.6f, 70.1f),
      new Country("MA", "Morocco", "Морокко", R.drawable.flag_ma, 74.3f, 75.4f, 73.3f),
//      new Country("MC", "Monaco", "+377", R.drawable.flag_mc),
      new Country("MD", "Moldova", "Молдова", R.drawable.flag_md, 72.1f, 76.2f, 67.9f),
      new Country("ME", "Montenegro", "Черногория", R.drawable.flag_me, 76.1f, 78.1f, 74.1f),
//      new Country("MF", "Saint Martin", "+590", R.drawable.flag_mf),
      new Country("MG", "Madagascar", "Мадагаскар", R.drawable.flag_mg, 65.5f, 67f, 63.9f),
//      new Country("MH", "Marshall Islands", "+692", R.drawable.flag_mh),
      new Country("MK", "Macedonia", "Македония", R.drawable.flag_mk, 75.7f, 77.8f, 73.5f),
      new Country("ML", "Mali", "Мали", R.drawable.flag_ml, 58.2f, 58.3f, 58.2f),
      new Country("MM", "Myanmar", "Мьянма", R.drawable.flag_mm, 66.6f, 68.5f, 64.6f),
      new Country("MN", "Mongolia", "Монголия", R.drawable.flag_mn, 68.8f, 73.2f, 64.7f),
//      new Country("MO", "Macao", "+853", R.drawable.flag_mo),
//      new Country("MP", "Northern Mariana Islands", "+1", R.drawable.flag_mp),
//      new Country("MQ", "Martinique", "+596", R.drawable.flag_mq),
      new Country("MR", "Mauritania", "Мавритания", R.drawable.flag_mr, 63.1f, 64.6f, 61.6f),
//      new Country("MS", "Montserrat", "+1", R.drawable.flag_ms),
      new Country("MT", "Malta", "Мальта", R.drawable.flag_mt, 81.7f, 83.7f,  79.7f),
      new Country("MU", "Mauritius", "Маврикий", R.drawable.flag_mu, 74.6f, 77.8f, 71.4f),
      new Country("MV", "Maldives", "Мальдивы", R.drawable.flag_mv, 78.5f, 80.2f, 76.9f),
      new Country("MW", "Malawi", "Малави", R.drawable.flag_mw, 58.3f, 59.9f, 56.7f),
      new Country("MX", "Mexico", "Мексика", R.drawable.flag_mx, 76.7f, 79.5f, 73.9f),
      new Country("MY", "Malaysia", "Малазия", R.drawable.flag_my, 75f, 77.3f, 72.7f),
      new Country("MZ", "Mozambique", "Мозамбик", R.drawable.flag_mz, 57.6f, 59.4f, 55.7f),
      new Country("NA", "Namibia", "Намибия", R.drawable.flag_na, 65.8f, 68.3f, 63.1f),
//      new Country("NC", "New Caledonia", "+687", R.drawable.flag_nc),
      new Country("NE", "Niger", "Нигер", R.drawable.flag_ne, 61.8f, 62.8f, 60.9f),
//      new Country("NF", "Norfolk Island", "+672", R.drawable.flag_nf),
      new Country("NG", "Nigeria", "Нигерия", R.drawable.flag_ng, 54.5f, 55.6f, 53.4f),
      new Country("NI", "Nicaragua", "Никарагуа", R.drawable.flag_ni, 74.8f, 77.9f, 71.5f),
      new Country("NL", "Netherlands", "Нидерланды", R.drawable.flag_nl, 81.9f, 83.6f, 80f),
      new Country("NO", "Norway", "Норвегия", R.drawable.flag_no, 81.8f, 83.7f, 79.8f),
      new Country("NP", "Nepal", "Непал", R.drawable.flag_np, 69.2f, 70.8f, 67.7f),
//      new Country("NR", "Nauru", "+674", R.drawable.flag_nr),
//      new Country("NU", "Niue", "+683", R.drawable.flag_nu),
      new Country("NZ", "New Zealand", "Новая Зеландия", R.drawable.flag_nz, 81.6f, 83.3f, 80),
      new Country("OM", "Oman", "Оман", R.drawable.flag_om, 76.6f, 79.2f, 75f),
      new Country("PA", "Panama", "Панама", R.drawable.flag_pa, 77.8f, 81.1f, 74.7f),
      new Country("PE", "Peru", "Перу", R.drawable.flag_pe, 75.5f, 78f, 73.1f),
//      new Country("PF", "French Polynesia", "+689", R.drawable.flag_pf),
      new Country("PG", "Papua New Guinea", "Папуа - Новая Гвинея", R.drawable.flag_pg, 62.9f, 65.4f, 60.6f),
      new Country("PH", "Philippines", "Филиппины", R.drawable.flag_ph, 68.5f, 72f, 65.3f),
      new Country("PK", "Pakistan", "Пакистан", R.drawable.flag_pk, 66.4f, 67.5f, 65.5f),
      new Country("PL", "Poland", "Польша", R.drawable.flag_pl, 77.5f, 81.3f, 73.6f),
//      new Country("PM", "Saint Pierre and Miquelon", "+508", R.drawable.flag_pm),
//      new Country("PN", "Pitcairn", "+872", R.drawable.flag_pn),
//      new Country("PR", "Puerto Rico", "+1", R.drawable.flag_pr),
//      new Country("PS", "Palestinian Territory, Occupied", "+970", R.drawable.flag_ps),
      new Country("PT", "Portugal", "Португалия", R.drawable.flag_pt, 81.1f, 83.9f, 78.2f),
//      new Country("PW", "Palau", "+680", R.drawable.flag_pw),
      new Country("PY", "Paraguay", "Парагвай", R.drawable.flag_py, 74f, 76f, 72.2f),
      new Country("QA", "Qatar", "Катр", R.drawable.flag_qa, 78.2f, 80f, 77.4f),
//      new Country("RE", "Réunion", "+262", R.drawable.flag_re),
      new Country("RO", "Romania", "Румыния", R.drawable.flag_ro, 75f, 78.8f, 71.4f),
      new Country("RS", "Serbia", "Сербия", R.drawable.flag_rs, 75.6f, 78.4f, 72.9f),
      new Country("RU", "Russia", "Россия", R.drawable.flag_ru, 70.5f, 76.3f, 64.7f),
      new Country("RW", "Rwanda", "Руанда", R.drawable.flag_rw, 66.1f, 71.1f, 60.9f),
      new Country("SA", "Saudi Arabia", "Саудовская Аравия", R.drawable.flag_sa, 74.5f, 76f, 73.2f),
      new Country("SB", "Solomon Islands", "Соломоновы Острова", R.drawable.flag_sb, 69.2f, 70.8f, 67.9f),
      new Country("SC", "Seychelles", "Сейшельские Острова", R.drawable.flag_sc, 73.2f, 78f, 69.1f),
      new Country("SD", "Sudan", "Судан", R.drawable.flag_sd, 64.1f, 65.9f, 62.4f),
      new Country("SE", "Sweden", "Швеция", R.drawable.flag_se, 82.4f, 84f, 80.7f),
      new Country("SG", "Singapore", "Сингапур", R.drawable.flag_sg, 83.1f, 86.1f, 80f),
//      new Country("SH", "Saint Helena, Ascension and Tristan Da Cunha", "+290", R.drawable.flag_sh),
      new Country("SI", "Slovenia", "Словения", R.drawable.flag_si, 80.8f, 82.5f, 78.6f),
//      new Country("SJ", "Svalbard and Jan Mayen", "+47", R.drawable.flag_sj),
      new Country("SK", "Slovakia", "Словакия", R.drawable.flag_sk, 76.7f, 80.2f, 72.9f),
      new Country("SL", "Sierra Leone", "Сьерра-Леоне", R.drawable.flag_sl, 50.1f, 50.8f, 49.3f),
//      new Country("SM", "San Marino", "+378", R.drawable.flag_sm),
      new Country("SN", "Senegal", "Сенегал", R.drawable.flag_sn, 66.7f, 68.6f, 64.6f),
      new Country("SO", "Somalia", "Сомали", R.drawable.flag_so, 55f, 56.6f, 53.5f),
      new Country("SR", "Suriname", "Суринам", R.drawable.flag_sr, 71.6f, 74.7f, 68.6f),
      new Country("SS", "South Sudan", "Южный Судан", R.drawable.flag_ss, 57.3f, 58.6f, 56.1f),
      new Country("ST", "Sao Tome and Principe", "Сан-Томе и Принсипи", R.drawable.flag_st, 67.5f, 69.4f, 65.6f),
      new Country("SV", "El Salvador", "Сальвадор", R.drawable.flag_sv, 73.5f, 77.9f, 68.8f),
//      new Country("SX", "  Sint Maarten", "+1", R.drawable.flag_sx),
      new Country("SY", "Syria", "Сирия", R.drawable.flag_sy, 64.5f, 69.9f, 59.9f),
      new Country("SZ", "Swaziland", "Свазиленд", R.drawable.flag_sz, 58.9f, 61.1f, 56.6f),
//      new Country("TC", "Turks and Caicos Islands", "+1", R.drawable.flag_tc),
      new Country("TD", "Chad", "Чад", R.drawable.flag_td, 53.1f, 54.5f, 51.7f),
//      new Country("TF", "French Southern Territories", "+262", R.drawable.flag_tf),
      new Country("TG", "Togo", "Того", R.drawable.flag_tg, 59.9f, 61.1f, 58.6f),
      new Country("TH", "Thailand", "Таиланд", R.drawable.flag_th, 74.9f, 78f, 71.9f),
      new Country("TJ", "Tajikistan", "Таджикистан", R.drawable.flag_tj, 69.7f, 73.6f, 66.6f),
//      new Country("TK", "Tokelau", "+690", R.drawable.flag_tk),
      new Country("TL", "Timor-Leste", "Восточный Тимор", R.drawable.flag_tl, 68.3f, 70.1f, 66.6f),
      new Country("TM", "Turkmenistan", "Туркменистан", R.drawable.flag_tm, 66.3f, 70.5f, 62.2f),
      new Country("TN", "Tunisia", "Тунис", R.drawable.flag_tn, 75.3f, 77.8f, 73f),
      new Country("TO", "Tonga", "Тонга", R.drawable.flag_to, 73.5f, 76.4f, 70.6f),
      new Country("TR", "Turkey", "Турция", R.drawable.flag_tr, 75.8f, 78.9f, 72.6f),
      new Country("TT", "Trinidad and Tobago", "Тринидад и Тобаго", R.drawable.flag_tt, 71.2f, 74.8f, 67.9f),
//      new Country("TV", "Tuvalu", "+688", R.drawable.flag_tv),
//      new Country("TW", "Taiwan", "+886", R.drawable.flag_tw),
      new Country("TZ", "Tanzania", "Танзания", R.drawable.flag_tz, 61.8f, 63.8f, 59.9f),
      new Country("UA", "Ukraine", "Украина", R.drawable.flag_ua, 71.3f, 76.1f, 66.3f),
      new Country("UG", "Uganda", "Уганда", R.drawable.flag_ug, 62.3f, 64.3f, 60.3f),
//      new Country("UM", "U.S. Minor Outlying Islands", "", R.drawable.flag_um),
      new Country("US", "United States", "США", R.drawable.flag_us, 79.3f, 81.6f, 76.9f),
      new Country("UY", "Uruguay", "Уругвай", R.drawable.flag_uy, 77f, 80.4f, 73.3f),
      new Country("UZ", "Uzbekistan", "Узбекистан", R.drawable.flag_uz, 69.4f, 72.7f, 66.1f),
//      new Country("VA", "Holy See (Vatican City State)", "+379", R.drawable.flag_va),
      new Country("VC", "Saint Vincent and the Grenadines", "Сент-Винсента и Гренадин", R.drawable.flag_vc, 73.2f, 75.2f, 71.3f),
      new Country("VE", "Venezuela", "Венесуэла", R.drawable.flag_ve, 74.1f, 78.5f, 70f),
//      new Country("VG", "Virgin Islands, British", "+1", R.drawable.flag_vg),
//      new Country("VI", "Virgin Islands, U.S.", "+1", R.drawable.flag_vi),
      new Country("VN", "Vietnam", "Вьетнам", R.drawable.flag_vn, 76f, 80.7f, 71.3f),
      new Country("VU", "Vanuatu", "Вануатау", R.drawable.flag_vu, 72f, 74f, 70.1f),
//      new Country("WF", "Wallis and Futuna", "+681", R.drawable.flag_wf),
      new Country("WS", "Samoa", "Самоа", R.drawable.flag_ws, 74f, 77.5f, 70.9f),
//      new Country("XK", "Kosovo", "+383", R.drawable.flag_xk),
      new Country("YE", "Yemen", "Йемен", R.drawable.flag_ye, 65.7f, 67.2f, 64.3f),
//      new Country("YT", "Mayotte", "+262", R.drawable.flag_yt),
      new Country("ZA", "South Africa", "ЮАР", R.drawable.flag_za, 62.9f, 66.2f, 59.3f),
      new Country("ZM", "Zambia", "Замбия", R.drawable.flag_zm, 61.1f, 64.7f, 59f),
      new Country("ZW", "Zimbabwe", "+Зимбабве", R.drawable.flag_zw, 60.7f, 62.3f, 59f),
  };

  private String nameISO;
  private String nameENG;
  private String nameRUS;
  private int flag = -1;
  private float sexesLife;
  private float sexesFemale;
  private float sexesMale;

  public Country(String nameISO, String nameENG, String nameRUS, int flag, float sexesLife, float sexesFemale, float sexesMale) {
    this.nameISO = nameISO;
    this.nameENG = nameENG;
    this.nameRUS = nameRUS;
    this.flag = flag;
    this.sexesLife = sexesLife;
    this.sexesFemale = sexesFemale;
    this.sexesMale = sexesMale;
  }

  public Country() {
  }

  public String getNameISO() {
    return nameISO;
  }

  public String getNameENG() {
    return nameENG;
  }

  public String getNameRUS() {
    return nameRUS;
  }

  public int getFlag() {
    return flag;
  }

  public float getSexesLife() {
    return sexesLife;
  }

  public float getSexesFemale() {
    return sexesFemale;
  }

  public float getSexesMale() {
    return sexesMale;
  }

  public void loadFlagByCode(Context context) {
    if (this.flag != -1)
      return;

    try {
      this.flag = context.getResources()
          .getIdentifier("flag_" + this.nameISO.toLowerCase(Locale.ENGLISH), "drawable",
              context.getPackageName());
    } catch (Exception e) {
      e.printStackTrace();
      this.flag = -1;
    }
  }
    /*
     *      GENERIC STATIC FUNCTIONS
     */

  private static List<Country> allCountriesList;

  public static List<Country> getAllCountries() {
    if (allCountriesList == null) {
      allCountriesList = Arrays.asList(COUNTRIES);
    }
    return allCountriesList;
  }

  public static Country getCountryByNameENG(String nameENG) {
    for (Country c : COUNTRIES) {
      if (nameENG.equals(c.getNameENG())) {
        return c;
      }
    }
    return null;
  }

  public static Country getCountryByNameRUS(String nameRUS) {
    for (Country c : COUNTRIES) {
      if (nameRUS.equals(c.getNameRUS())) {
        return c;
      }
    }
    return null;
  }
    /*
     * COMPARATORS
     */

  public static final Comparator<Country> COMPARATOR_BY_ISO = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return country1.nameISO.compareTo(country2.nameISO);
    }
  };

  public static final Comparator<Country> COMPARATOR_BY_NAME_ENG = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return country1.nameENG.compareTo(country2.nameENG);
    }
  };

  public static final Comparator<Country> COMPARATOR_BY_NAME_RUS = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return country1.nameRUS.compareTo(country2.nameRUS);
    }
  };

  public static final Comparator<Country> COMPARATOR_BY_SEXES_LIFE = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return Float.compare(country1.sexesLife, country2.sexesLife);
    }
  };

  public static final Comparator<Country> COMPARATOR_BY_SEXES_FEMALE = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return Float.compare(country1.sexesFemale, country2.sexesFemale);
    }
  };

  public static final Comparator<Country> COMPARATOR_BY_SEXES_MALE = new Comparator<Country>() {
    @Override
    public int compare(Country country1, Country country2) {
      return Float.compare(country1.sexesMale, country2.sexesMale);
    }
  };


}