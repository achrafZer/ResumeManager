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

        Person rafik = new Person();
        rafik.setFirstName("Rafik");
        rafik.setLastName("CHAIB");
        Date rafikBirthday = sdf.parse("1999-02-04");
        rafik.setBirthDate(rafikBirthday);
        rafik.setEmail("rafik.chaib@univ-amu.fr");
        rafik.setPassword("RafikPassword");
        personService.savePerson(rafik);

        Person maxime = new Person();
        maxime.setFirstName("Maxime");
        maxime.setLastName("GUILIANI");
        Date maximeBirthday = sdf.parse("1999-07-04");
        maxime.setBirthDate(maximeBirthday);
        maxime.setEmail("maxime.guiliani@univ-amu.fr");
        maxime.setPassword("MaximePassword");
        personService.savePerson(maxime);

        Person diego = new Person();
        diego.setFirstName("Diego");
        diego.setLastName("IMBERT");
        Date diegoBirthday = sdf.parse("2001-08-04");
        diego.setBirthDate(diegoBirthday);
        diego.setEmail("diego.imbert@univ-amu.fr");
        diego.setPassword("DiegoPassword");
        personService.savePerson(diego);

        Person kpotivi = new Person();
        kpotivi.setFirstName("Kpotivi");
        kpotivi.setLastName("KPOTY");
        Date kpotiviBirthday = sdf.parse("1997-01-04");
        kpotivi.setBirthDate(kpotiviBirthday);
        kpotivi.setEmail("kpotivi.kpoty@univ-amu.fr");
        kpotivi.setPassword("KpotiviPassword");
        personService.savePerson(kpotivi);

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


