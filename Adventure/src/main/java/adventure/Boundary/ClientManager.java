/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.Boundary;

import adventure.Entity.objects.AdvObject;
import adventure.identifiers.ObjectId;
import adventure.Entity.types.GameDescription;
import adventure.Entity.types.RobberyAdventure;
import adventure.utilities.DatabaseGameTable;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Paolo
 */
public class ClientManager {
    
    private final WebTarget target;
    
    public void addObjectRequest(AdvObject object){
        
        Gson gson = new Gson();
        String jsonObject = gson.toJson(object);
        target.path("object/add").request(MediaType.APPLICATION_JSON).put(Entity.entity(jsonObject, MediaType.APPLICATION_JSON));
    }
    
    public AdvObject getObjectRequest(ObjectId objectId){
        Response response = target.path("object/" + objectId.name()).request(MediaType.APPLICATION_JSON).get();
	AdvObject object = response.readEntity(AdvObject.class);
	
	response.close();
	return object;
    }
    
    public ClientManager(String target){
        Client client = ClientBuilder.newClient();
        this.target = client.target(target);
    }
    
    public boolean addGameSavingRequest(String gameId, GameDescription gameDescription){
        
        Gson gson = new Gson();
        DatabaseGameTable dbGameTable = new DatabaseGameTable((RobberyAdventure)gameDescription, gameId);
        String jsonGameTable = gson.toJson(dbGameTable, DatabaseGameTable.class);
        
        Response response = target.path("game/add").request(MediaType.APPLICATION_JSON).put(Entity.entity(jsonGameTable, MediaType.APPLICATION_JSON));
        return response.readEntity(Boolean.class);
        
    }
    
    public RobberyAdventure getGameSavingRequest(String gameId){
        
        Response response = target.path("game/" + gameId).request(MediaType.APPLICATION_JSON).get();
        return response.readEntity(RobberyAdventure.class);
    }
    
    public Set<String> getAllGamesNamesRequest(){
        
        Response response = target.path("game/allnames").request(MediaType.APPLICATION_JSON).get();
        
        GenericType<Set<String>> genericType = new GenericType<Set<String>>(){};
        
        return response.readEntity(genericType);
    }
    
    public void populate(){
        
        String[] aliasArray = {"bendaggi"};
        Set<String> alias = new HashSet<>(Arrays.asList(aliasArray));
        AdvObject object = new AdvObject(ObjectId.BANDAGES, "bende", "Delle bende sporche, "
        + "utilizzate chissà dove, vedi tu per cosa le puoi usare", alias);
        addObjectRequest(object);
        
        
        
        aliasArray = new String[]{"sapone"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BAR_OF_SOAP, "saponetta", "Una saponetta di qualche anno fa,"
                + "non ci puoi fare niente!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BATHROOM_DOOR, "porta", "La porta del bagno del signore, strana, ruvida e rovinata", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"finestrone"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BATHROOM_WINDOW, "finestra", "La finestra del bagno al piano terra, forse puoi farci un salto...in qualche modo!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"pile"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BATTERIES, "batterie", "Delle batterie che sembrano scariche, forse le puoi ricaricare con un caricabatterie!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{"caricabatterie"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BATTERY_CHARGER, "caricatore", "Un caricatore, forse puoi usarlo per caricare delle pile...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"comodino"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BEDSIDE_TABLE, "comodino", "Un comodino con delle preziosissime fedi sopra...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"panca piana"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BENCH, "panca", "Una panca piana da palestra, ti ricordi ancora come si usano i muscoli?", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BIDET, "bidet", "Smettila di guardare il bidet e cerca i soldi...forse nel gabinetto!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BOOKCASE, "libreria", "Una libreria enorme, ma ti ricordi cosa sono i libri?", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"braccialetto"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BRACELET, "bracciale", "Hai trovato un bracciale d'oro massiccio, prenditelo subito!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"pulsante"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.BUTTON, "bottone", "Un bottone con dietro la porta nascosta della biblioteca...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"veicolo", "vettura"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.CAR, "macchina", "Non la puoi spostare, ma forse puoi prenderti la benzina dalla bocchetta di rifornimento!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"seggiole"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.CHAIRS, "sedie", "Sedie normalissime, non credo ti possano interessare!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"scrigno"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.CHEST, "baule", "Un baule di legno antico, chissà che ci sarà dentro...", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{"quadro"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DANCE, "Danza di Matisse", "Lo splendido quadro di Matisse, con 5 danzatori...chissà se ha questo numero come password di qualcosa...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"detergente", "sapone"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DETERGENT, "detersivo", "Un semplice detersivo per lavatrici...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"piattini"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DISHES, "patti", "Dei piatti con del cibo ancora dentro, che disordine!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DISPLAY_CABINET, "cristalliera","La cristalliera del nonno del magnate...un'enorme quantita' di oggetti di cristallo dentro...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"cuccia del cane"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DOGHOUSE, "cuccia","La cuccia del cane del magnate...ma il cane non si fa vedere...c'è solo una medaglietta dentro...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DOG_TAG, "medaglietta","La medaglietta del cane....guss a caratteri cubitali...forse ti può essere utile!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DOORKNOB, "pomello","Il pomello della porta della stanza dei quadri...è molto strano...prova ad aprire la porta usandolo!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"letto"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DOUBLE_BED, "letto matrimoniale","Un letto megalodontico, chissà chi l'ha progettato....ovviamente non puoi rubarlo", alias);
        addObjectRequest(object);
        
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DRAWER, "cassetto","Il cassetto del lavandino...potrebbe contenere bende o altre cose utili...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DRILL, "trapano","Un vecchio trapano a pile, per attivarlo sicuramente ne avrai bisogno...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.DUMBBELL, "manubrio","Un manubrio da palestra....! chissà se lo specchio resiste al suo impatto...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.ELECTRIC_SAW, "sega elettrica","Una sega elettrica circolare già carica....chissà per cosa la usa...ma la finestra del bagno probabilmente vuole essere aperta in questo modo...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"scanner"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.FINGERPRINT_SCANNER, "scanner impronta digitale","Lo scanner di impronte digitali della porta blindata del vault...sicuramente non è il modo adatto per entrare nel vault....", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.FRUIT_BOWL, "portafrutta","Un portafrutta inutile....vattene da qua, devi rubare cose di valore..!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.FUEL_CAN, "tanica","Una tanica che sà di benzina....sarebbe curioso prendere in prestito la benzina dalla macchina...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"boccuccia"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.FUEL_FILLER_NECK, "bocchetta di rifornimento","la bocchetta di rifornimento della macchina....aprila e fai succedere qualcosa...", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.GARAGE_DOOR, "porta","Penso sia una porta inutilizzabile...non puoi andare da nessuna parte...", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.GARAGE_KEY, "chiave","Una chiave strana....penso non sia fatta per aprire la porta d'ingresso...", alias);
        addObjectRequest(object);
                
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.GARAGE_SHUTTER, "serranda","La serranda del garage....con una serratura che vuole essere aperta...qui vicino dovrebbero esserci delle chiavi...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"lingotti d'oro"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.GOLD_INGOTS, "lingotti","Un pregiato quantitativo di lingotti d'oro....cosa aspetti...! prenditeli e aggiungi tutto alla refurtiva....!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.HAMMER, "martello","Un martello...sicuramente utile per rompere qualche specchio...divertiti un po'...!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.HAND_RAIL, "corrimano","Uno strano corrimano vicino allo specchio...prova a usarlo...vedi che succede!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{"tastierino"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.INNER_NUMER_KEYPAD, "tastierino numerico interno","Il tastierino numerico della cassaforte....prova a trovare la combinazione di 5 caratteri...hai tutti gli elementi!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.INNER_SAFE, "cassaforte interna","Un'altra cassaforte da aprire...quel tale del magnate avrà sicuramente nascosto altre chiavi qui dentro...", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.JEWELS, "gioielli","Gioielli di famiglia del magnate...ma vuoi prenderteli o no....! non farmi arrabbiare!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.KITCHEN_DOOR, "porta","La porta della cucina...innocua...vedi tu cosa vuoi fare...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.KITCHEN_TABLE, "tavolo","Il grande tavolo della cucina, grandioso, con intagli antichi...chissà da dove l'hanno preso", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LAT_MACHINE, "macchinario dorso","Il vecchio macchinario usato da Arnold per fare spalle...il magnate stupisce sempre...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LAUNDRY_DOOR, "porta","La porta della lavanderia...molto strana...ahhh! un ragno sulla maniglia...!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LEFT_SHELVING_UNIT, "scaffale sinistro","Uno scaffale con un sacco di roba mai usata....c'è anche un detersivo mezzo vuoto!", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LIVINGROOM_TABLE, "tavolo ","Un tavolo con dei normalissimi oggetti sopra....protafrutta...mangia quello che vuoi!", alias);
        addObjectRequest(object);
        
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LIBRARY_DOOR, "porta","Una porta...niente di che! non vorrai mica rubartela, sciocco...!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LIVINGROOM_EAST_DOOR, "porta","Una porta molto ruvida...piena di strani segni...non interessarti, lascia perdere...", alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LIVINGROOM_WEST_DOOR, "porta","La porta della palestra....è blindata...ha una serratura che sembra molto difficile da scassinare...dovresti fare leva con qualcosa...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LOCKERS, "armadietti","Armadietti del caveau...non hanno caramelle all'interno...prenditi tutto quello che non è tuo...", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.LUGGAGE_OBJECTS_LIST, "lista oggetti valigia","La lista degli oggetti da portare in valigia! il magnate è in vacanza....quindi posso restare un altro po' a casa loro!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.MAIN_DOOR, "porta d'ingresso","La porta d'ingresso, maniglia placcata in oro, vetrate istoriate....incredibile...!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.MIRROR, "specchio","Uno specchio che ha all'aria di voler essere rotto...sento che il magnate ci ha nascosto qualcosa dietro!", alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"quotidiano"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.NEWSPAPER_PAGE, "giornale", "Un giornale vecchissimo...\"Elezione di Gorbachev....\".",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.OUTER_NUMERIC_KEYPAD, "tastierino numerico", "Il tastierino della cassaforte....sembra rotto...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.OUTER_SAFE, "cassaforte", "La cassaforte del magnate...un tastierino non funzionante...chissà cosa nasconderà al suo interno...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.PAINTINGS_ROOM_DOOR, "porta", "Una porta meravigliosa...chissa chi l'ha costruita...pomelli in strani materiali...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{"fotografie"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.PHOTO_ALBUM, "album fotografico", "Quante foto del magnate...come si è divertito! ora smettila...vai a rubare!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.POWER_UNIT, "generatore elettrico", "Questo enorme coso va a benzina, vedi di prendere la tanica, riempirla come si deve e iniziare ad attivarlo...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.RACK, "rastrelliera", "Una montagna di manubri...che fai? prendine qualcuno invece di non fare niente.....",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.REMOTE_CONTROL, "telecomando", "Il telecomando della televisione...davvero? nascosto nei canyon del divano...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.RIGHT_SHELVING_UNIT, "scaffale destro", "lo scaffale più pieno che tu abbia mai visto....qualche pila...diverse taniche...facci qualcosa!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"guanti"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.RUBBER_GLOVES, "guanti di gomma", "Guanti di gomma utilissimi per il futuro...forse avrai a che fare con materiali strani...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SAFE_ROLL_OF_BILLS, "mazzette di contanti", "Dollari! Dollari! Si prende tutto....!",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SECRET_ROOM_DOOR, "porta", "Effettivamente c'era una porta segreta tra la biblioteca e questa stanza!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"telecamera di sicurezza"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SECURITY_CAMERA, "telecamera", "Telecamera tecnologicamente super avanzata, ma una fionda le farebbe molto male...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SHELF, "mensola", "La mensola del garage...con maschere da fabbro e tubi di gomma....Ti potrebbero servire...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SHOPPING_LIST, "lista della spesa", "La lista della spesa del magnate...smettila di guardarla..ora!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SHOULDER_PRESS, "macchinario spalle", "Il famoso mscchinario per le spalle di Arnold...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SHOWER, "doccia", "Una semplice doccia...non lavarti e rimani sporco...devi andare a rubare!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SINK, "lavandino", "il lavabo della cucina! lavabo...che strana parola...e non continuare a guardare troppo!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SLING, "fionda", "Una fionda...utile per rompere telecamere e nient'altro!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SPOON, "cucchiaio", "Che bel cucchiaio.... da quanto non ne vedi uno cosi', è speciale...forse ti serve per aprire qualcosa...!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.SOFA, "divano", "un divano grazioso, ma rimane sempre un divano...non puoi mica prendertelo!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"scala a pioli"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.STEP_LADDER,"scala", "Una scala a pioli...chissa' dove arrivi se riesci a usarla...sfaticato!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.THERMAL_LANCE,"lancia termica", "Una bellissima lancia termica...ottima per aprire le porte dei caveau....Hai sicuramente bisogno di un generatore elettrico attivo per riuscire a farla funzionare!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.THE_DEATH_OF_MARAT,"Morte di marat", "La morte di Marat...quadro grandioso...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.THE_KISS,"Bacio", "Il bacio di Hayez..quadro grandioso...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.THE_SCREAM,"Urlo", "L'urlo di Munch..quadro grandioso...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.THE_STARRY_NIGHT,"Notte stellata", "La notte stellata di Van Gogh...questo quadro vale troppo per essere lasciato al magnate...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"cassetta dello scarico"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TOILET_TANK,"cassetta", "La cassetta dello scarico del WC...la puzza e' davvero troppa...fai in fretta, qui dentro sicuramente c'e' qualcosa di valore!",alias);
        addObjectRequest(object);
        
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TOOL_CABINET,"armadio", "L'armadio degli attrezzi del magnate...ma quante cose ha nel garage?!",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TRAPDOOR,"botola", "Una botola che si nascondeva benissimo...usala per scoprire altre stanze...chissa' che altre cose nasconde...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TRAPDOOR_KEY,"chiave botola", "Un'altra chiave per aprire qualcosa...sembra fatta apposta per una botola...",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TUBE,"tubo", "Un tubo di gomma...chissà se puoi riempire la tenica di benzina con questo....",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{"tv"};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TV,"televisione", "Una televisione enorme...ma è fissata bene al muro...forse hai voglia di accenderla?",alias);
        addObjectRequest(object);
        
        
                
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.VASE,"vaso", "Un vaso molto strano all'ingresso....sembra fatto apposta per nascondere qualcosa!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.VAULT_DOOR,"porta", "Una porta enorme, blindata, protetta dallo scanner delle impronte digitali...ma la lancia termica potrebbe farci un pensierino...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.VAULT_ROLL_OF_BILLS,"mazzette di contanti", "Altre mazzette di contanti, dollari! prendi tutto...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.TOWEL,"asciugamano", "Asciugmano inutle...passa avanti! non ti fermare a pensare!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.VOCAZIONE_SAN_MATTEO,"Vocazione di San Matteo", "La vocazione di San Matteo del Caravaggio....quadro a dir poco stupendo!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WARDROBE,"cabina armadio", "La cabina armadio lussosissima della camera del magnate...",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WASHING_MACHINE,"lavatrice", "Una normalissima lavatrice...ma senti un prurito alle mani...la vuoi spostare!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WASH_BASIN,"lavandino", "Un lavandino normalissimo...inutile e stupido come te...continua a rubare che è meglio!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WC,"gabinetto", "Il gabinetto del magnate con una sporgente cassetta dello scarico....che puzza...ha lasciato qualcosa di scuro nel water!",alias);
        addObjectRequest(object);
        
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WEDDING_RINGS,"fedi", "Le fedi preziosissime del magnate e della moglie...giù nello zaino!",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WELDING_MASK,"maschera da saldatore", "Una maschera utile per usare lance termiche...e magari aprire porte particolarmente resistenti!",alias);
        addObjectRequest(object);
        
        aliasArray = new String[]{};
        alias = new HashSet<>(Arrays.asList(aliasArray));
        object = new AdvObject(ObjectId.WRENCH,"chiave inglese", "Una chiave inglese...può essere sempre utile...",alias);
        addObjectRequest(object);
        
        
        
        
    }
    
    
    
}
