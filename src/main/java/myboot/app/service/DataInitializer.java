package myboot.app.service;

import myboot.app.model.Activity;
import myboot.app.model.CV;
import myboot.app.model.Person;
import myboot.app.model.XUser;
import myboot.app.model.ActivityNature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

@Service
//@Profile("dev")
public class DataInitializer {

    @Autowired
    PersonService personService;

    @Autowired
    CVService cvService;

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;

    @PostConstruct
    public void initData() throws ParseException {
        if (!personService.getAllPersons().isEmpty()) return;

        Person achraf = new Person();
        achraf.setFirstName("Achraf");
        achraf.setLastName("ZERHOUNI");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sdf.parse("2000-12-25");
        achraf.setBirthDate(birthday);
        achraf.setEmail("zerhouniachraf@hotmail.com");
        achraf.setPassword("AchrafPassword");
        personService.savePerson(achraf);

        XUser achrafUser = new XUser();
        achrafUser.setUserName("zerhouniachraf@hotmail.com");
        achrafUser.setPassword("AchrafPassword");
        achrafUser.setRoles(new HashSet<>());
        achrafUser.getRoles().add("ADMIN");
        userService.saveUser(achrafUser);

        CV cvAchraf = new CV();
        cvAchraf.setPerson(achraf);
        cvService.saveCV(cvAchraf);

        Activity activity1 = new Activity();
        activity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity1.setTitle("Chef de projets informatiques");
        activity1.setDescription("En étant chef de projets informatiques à la Métropole, je m'occupe de préparer les" + " environnement de travail pour mon équipe de développement, de garantir le bon déroulement de la" + " méthode AGILE, ainsi que d'anticiper les moments où je dois intervenir dans le code");
        activity1.setStartYear(2023);
        activity1.setEndYear(2024);
        activity1.setCv(cvAchraf);
        activityService.saveActivity(activity1);

        Activity activity2 = new Activity();
        activity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        activity2.setTitle("Equipier polyvalent");
        activity2.setDescription("En étant équipier plyvalent en restauration, je m'occupais de différents posts en fonction du beoin. Des fois en cuisine, des fois à la caisse et des fois je devais uniquement assister les clients durant leurs commandes");
        activity2.setStartYear(2021);
        activity2.setEndYear(2023);
        activity2.setCv(cvAchraf);

        activityService.saveActivity(activity2);

        Activity activity3 = new Activity();
        activity3.setNature(ActivityNature.EDUCATION);
        activity3.setTitle("Licence, Informatique");
        activity3.setDescription("Durant ma licence, j'ai appris les éléments suivants : Intelligence artificielle, appliquée sur un jeu d'échecs · Java: Création d'un jeu d'échecs · Programmation orientée objet (POO)");
        activity3.setStartYear(2019);
        activity3.setEndYear(2022);
        activity3.setCv(cvAchraf);

        activityService.saveActivity(activity3);

        Activity activity4 = new Activity();
        activity4.setNature(ActivityNature.EDUCATION);
        activity4.setTitle("Master, Informatique");
        activity4.setDescription("Durant mon master, j'ai appris les éléments suivants : Framework Spring · Enterprise JavaBeans · Threads · Fiabilité logicielle à l'aide de Gradle en java ");
        activity4.setStartYear(2022);
        activity4.setEndYear(2024);
        activity4.setCv(cvAchraf);

        activityService.saveActivity(activity4);


        Person heba = new Person();
        heba.setFirstName("Heba");
        heba.setLastName("ABU RABIA");
        Date hebaBirthday = sdf.parse("2000-08-22");
        heba.setBirthDate(hebaBirthday);
        heba.setEmail("heba.abu-rabia@univ-amu.fr");
        heba.setPassword("HebaPassword");
        personService.savePerson(heba);

        XUser hebaUser = new XUser();
        hebaUser.setUserName("heba.abu-rabia@univ-amu.fr");
        hebaUser.setPassword("HebaPassword");
        hebaUser.setRoles(new HashSet<>());
        hebaUser.getRoles().add("USER");
        userService.saveUser(hebaUser);

        CV cvHeba = new CV();
        cvHeba.setPerson(heba);
        cvService.saveCV(cvHeba);

        Activity hactivity1 = new Activity();
        hactivity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        hactivity1.setTitle("Full Stack Developer");
        hactivity1.setDescription("Modul-Bio · Contrat en alternance");
        hactivity1.setStartYear(2023);
        hactivity1.setEndYear(2024);
        hactivity1.setCv(cvHeba);
        activityService.saveActivity(hactivity1);

        Activity hactivity2 = new Activity();
        hactivity2.setNature(ActivityNature.EDUCATION);
        hactivity2.setTitle("Aix-Marseille Université");
        hactivity2.setDescription("French language");
        hactivity2.setStartYear(2016);
        hactivity2.setEndYear(2017);
        hactivity2.setCv(cvHeba);

        activityService.saveActivity(hactivity2);

        Activity hactivity3 = new Activity();
        hactivity3.setNature(ActivityNature.EDUCATION);
        hactivity3.setTitle("Bachelor of Science");
        hactivity3.setDescription("BS, Computer Science");
        hactivity3.setStartYear(2019);
        hactivity3.setEndYear(2022);
        hactivity3.setCv(cvHeba);

        activityService.saveActivity(hactivity3);

        Activity hactivity4 = new Activity();
        hactivity4.setNature(ActivityNature.EDUCATION);
        hactivity4.setTitle("Master's degree'");
        hactivity4.setDescription("Computer Software Engineering");
        hactivity4.setStartYear(2022);
        hactivity4.setEndYear(2024);
        hactivity4.setCv(cvHeba);

        activityService.saveActivity(hactivity4);

        Person walid = new Person();
        walid.setFirstName("Walid");
        walid.setLastName("ADDOUCHE");
        Date walidBirthday = sdf.parse("2000-03-04");
        walid.setBirthDate(walidBirthday);
        walid.setEmail("walid.addouche@univ-amu.fr");
        walid.setPassword("WalidPassword");
        personService.savePerson(walid);

        XUser walidUser = new XUser();
        walidUser.setUserName("walid.addouche@univ-amu.fr");
        walidUser.setPassword("WalidPassword");
        walidUser.setRoles(new HashSet<>());
        walidUser.getRoles().add("USER");
        userService.saveUser(walidUser);

        CV cvWalid = new CV();
        cvWalid.setPerson(walid);
        cvService.saveCV(cvWalid);

        Activity wactivity1 = new Activity();
        wactivity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        wactivity1.setTitle("Ingénieur en développement Java");
        wactivity1.setDescription("Sopra Steria · Stage");
        wactivity1.setStartYear(2023);
        wactivity1.setEndYear(2024);
        wactivity1.setCv(cvWalid);
        activityService.saveActivity(wactivity1);

        Activity wactivity2 = new Activity();
        wactivity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        wactivity2.setTitle("Apprenti Ingénieur logiciel Java");
        wactivity2.setDescription("Capgemini · Contrat en alternance");
        wactivity2.setStartYear(2023);
        wactivity2.setEndYear(2024);
        wactivity2.setCv(cvWalid);

        activityService.saveActivity(wactivity2);

        Activity wactivity3 = new Activity();
        wactivity3.setNature(ActivityNature.EDUCATION);
        wactivity3.setTitle("Licence");
        wactivity3.setDescription("Informatique");
        wactivity3.setStartYear(2019);
        wactivity3.setEndYear(2022);
        wactivity3.setCv(cvWalid);

        activityService.saveActivity(wactivity3);

        Activity wactivity4 = new Activity();
        wactivity4.setNature(ActivityNature.EDUCATION);
        wactivity4.setTitle("Master2");
        wactivity4.setDescription("Ingénierie du développement Logiciel");
        wactivity4.setStartYear(2022);
        wactivity4.setEndYear(2024);
        wactivity4.setCv(cvWalid);

        activityService.saveActivity(wactivity4);


        ////////////////////////////////////////////////////////

        Person rafik = new Person();
        rafik.setFirstName("Rafik");
        rafik.setLastName("CHAIB");
        Date rafikBirthday = sdf.parse("1999-02-04");
        rafik.setBirthDate(rafikBirthday);
        rafik.setEmail("rafik.chaib@univ-amu.fr");
        rafik.setPassword("RafikPassword");
        personService.savePerson(rafik);

        XUser rafiqUser = new XUser();
        rafiqUser.setUserName("rafik.chaib@univ-amu.fr");
        rafiqUser.setPassword("RafikPassword");
        rafiqUser.setRoles(new HashSet<>());
        rafiqUser.getRoles().add("USER");
        userService.saveUser(rafiqUser);

        CV cvRafiq = new CV();
        cvRafiq.setPerson(rafik);
        cvService.saveCV(cvRafiq);

        Activity ractivity1 = new Activity();
        ractivity1.setNature(ActivityNature.EDUCATION);
        ractivity1.setTitle("Master 1");
        ractivity1.setDescription("Ingénierie Logicielle");
        ractivity1.setStartYear(2020);
        ractivity1.setEndYear(2022);
        ractivity1.setCv(cvRafiq);
        activityService.saveActivity(ractivity1);

        Activity ractivity2 = new Activity();
        ractivity2.setNature(ActivityNature.EDUCATION);
        ractivity2.setTitle("Master 1");
        ractivity2.setDescription("Ingénierie Logicielle");
        ractivity2.setStartYear(2022);
        ractivity2.setEndYear(2024);
        ractivity2.setCv(cvRafiq);

        activityService.saveActivity(ractivity2);

        Person maxime = new Person();
        maxime.setFirstName("Maxime");
        maxime.setLastName("GUILIANI");
        Date maximeBirthday = sdf.parse("1999-07-04");
        maxime.setBirthDate(maximeBirthday);
        maxime.setEmail("maxime.guiliani@univ-amu.fr");
        maxime.setPassword("MaximePassword");
        personService.savePerson(maxime);

        XUser maximeUser = new XUser();
        maximeUser.setUserName("maxime.guiliani@univ-amu.fr");
        maximeUser.setPassword("MaximePassword");
        maximeUser.setRoles(new HashSet<>());
        maximeUser.getRoles().add("USER");
        userService.saveUser(maximeUser);

        CV cvMaxime = new CV();
        cvMaxime.setPerson(maxime);
        cvService.saveCV(cvMaxime);

        Activity mactivity1 = new Activity();
        mactivity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        mactivity1.setTitle("Apprenti Java Full-stack");
        mactivity1.setDescription("Thales · Contrat en alternance");
        mactivity1.setStartYear(2023);
        mactivity1.setEndYear(2024);
        mactivity1.setCv(cvMaxime);
        activityService.saveActivity(mactivity1);

        Activity mactivity2 = new Activity();
        mactivity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        mactivity2.setTitle("Vacataire");
        mactivity2.setDescription("Direction générale des Finances publiques · Intérimaire");
        mactivity2.setStartYear(2023);
        mactivity2.setEndYear(2024);
        mactivity2.setCv(cvMaxime);

        activityService.saveActivity(mactivity2);

        Activity mactivity3 = new Activity();
        mactivity3.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        mactivity3.setTitle("Développeur junior");
        mactivity3.setDescription("NetCeler · Stage");
        mactivity3.setStartYear(2019);
        mactivity3.setEndYear(2022);
        mactivity3.setCv(cvMaxime);

        activityService.saveActivity(mactivity3);

        Person diego = new Person();
        diego.setFirstName("Diego");
        diego.setLastName("IMBERT");
        Date diegoBirthday = sdf.parse("2001-08-04");
        diego.setBirthDate(diegoBirthday);
        diego.setEmail("diego.imbert@univ-amu.fr");
        diego.setPassword("DiegoPassword");
        personService.savePerson(diego);

        XUser diegoUser = new XUser();
        diegoUser.setUserName("diego.imbert@univ-amu.fr");
        diegoUser.setPassword("DiegoPassword");
        diegoUser.setRoles(new HashSet<>());
        diegoUser.getRoles().add("USER");
        userService.saveUser(diegoUser);

        CV cvDiego = new CV();
        cvDiego.setPerson(diego);
        cvService.saveCV(cvDiego);

        Activity dactivity1 = new Activity();
        dactivity1.setNature(ActivityNature.EDUCATION);
        dactivity1.setTitle("Bachelor of Science");
        dactivity1.setDescription("BS, Computer Software Engineering");
        dactivity1.setStartYear(2019);
        dactivity1.setEndYear(2022);
        dactivity1.setCv(cvDiego);
        activityService.saveActivity(dactivity1);

        Activity dactivity2 = new Activity();
        dactivity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        dactivity2.setTitle("Software Engineer");
        dactivity2.setDescription("KANSO - Agence Digitale · Contrat en alternance");
        dactivity2.setStartYear(2023);
        dactivity2.setEndYear(2024);
        dactivity2.setCv(cvDiego);

        activityService.saveActivity(dactivity2);

        Person kpotivi = new Person();
        kpotivi.setFirstName("Kpotivi");
        kpotivi.setLastName("KPOTY");
        Date kpotiviBirthday = sdf.parse("1997-01-04");
        kpotivi.setBirthDate(kpotiviBirthday);
        kpotivi.setEmail("kpotivi.kpoty@univ-amu.fr");
        kpotivi.setPassword("KpotiviPassword");
        personService.savePerson(kpotivi);

        XUser kpotiviUser = new XUser();
        kpotiviUser.setUserName("kpotivi.kpoty@univ-amu.fr");
        kpotiviUser.setPassword("KpotiviPassword");
        kpotiviUser.setRoles(new HashSet<>());
        kpotiviUser.getRoles().add("USER");
        userService.saveUser(kpotiviUser);

        CV cvKpotivi = new CV();
        cvKpotivi.setPerson(kpotivi);
        cvService.saveCV(cvKpotivi);

        Activity kactivity1 = new Activity();
        kactivity1.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        kactivity1.setTitle("Développeur Full Stack Spring / Angular");
        kactivity1.setDescription("Sopra Steria");
        kactivity1.setStartYear(2023);
        kactivity1.setEndYear(2024);
        kactivity1.setCv(cvKpotivi);

        activityService.saveActivity(kactivity1);

        Activity kactivity2 = new Activity();
        kactivity2.setNature(ActivityNature.PROFESSIONAL_EXPERIENCE);
        kactivity2.setTitle("Stagiaire en développement Web");
        kactivity2.setDescription("HDM Network · Stage");
        kactivity2.setStartYear(2022);
        kactivity2.setEndYear(2023);
        kactivity2.setCv(cvKpotivi);

        activityService.saveActivity(kactivity2);

        Activity kactivity3 = new Activity();
        kactivity3.setNature(ActivityNature.EDUCATION);
        kactivity3.setTitle("Master 2, Ingénierie logicielle");
        kactivity3.setDescription("Aix-Marseille Université");
        kactivity3.setStartYear(2022);
        kactivity3.setEndYear(2024);
        kactivity3.setCv(cvKpotivi);

        activityService.saveActivity(kactivity3);






























        Person elie = new Person();
        elie.setFirstName("Elie");
        elie.setLastName("NICOLAS");
        Date elieBirthday = sdf.parse("1996-05-04");
        elie.setBirthDate(elieBirthday);
        elie.setEmail("elie.nicolas@univ-amu.fr");
        elie.setPassword("EliePassword");
        personService.savePerson(elie);

        Person juba = new Person();
        juba.setFirstName("Juba");
        juba.setLastName("OUARAB");
        Date jubaBirthday = sdf.parse("2000-04-04");
        juba.setBirthDate(jubaBirthday);
        juba.setEmail("juba.ouarab@univ-amu.fr");
        juba.setPassword("JubaPassword");
        personService.savePerson(juba);

        Person manal = new Person();
        manal.setFirstName("Manal");
        manal.setLastName("STIHI");
        Date manalBirthday = sdf.parse("2000-06-04");
        manal.setBirthDate(manalBirthday);
        manal.setEmail("manal.stihi@univ-amu.fr");
        manal.setPassword("ManalPassword");
        personService.savePerson(manal);

    }

}


