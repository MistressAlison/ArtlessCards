package ArtlessCards;

import basemod.*;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class ArtlessCardsMod implements PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(ArtlessCardsMod.class.getName());
    private static String modID;

    //This is for the in-game mod settings panel.
    private static final String MODNAME = "Artless Cards";
    private static final String AUTHOR = "Mistress Alison";
    private static final String DESCRIPTION = "Adds support for artless cards";
    
    // =============== SUBSCRIBE, INITIALIZE =================
    
    public ArtlessCardsMod() {
        logger.info("Subscribe to BaseMod hooks");
        
        BaseMod.subscribe(this);
      
        setModID("ArtlessCards");
        
        logger.info("Done subscribing");

        logger.info("Adding mod settings");

        //TODO mod settings here

        logger.info("Done adding mod settings");
        
    }
    
    public static void setModID(String ID) {
        modID = ID;
    }
    
    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        logger.info("========================= Initializing Artless Cards. =========================");
        ArtlessCardsMod artlessCardsMod = new ArtlessCardsMod();
        logger.info("========================= /Artless Cards Initialized/ =========================");
    }
    
    // ============== /SUBSCRIBE, INITIALIZE/ =================
    
    // =============== POST-INITIALIZE =================
    
    @Override
    public void receivePostInitialize() {

        logger.info("Loading badge image and mod options");
        
        // Load the Mod Badge
        Texture badgeTexture = new Texture("Badge.png");

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        //TODO load mod settings here
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        logger.info("Done loading badge Image and mod options");
    }
    
    // =============== / POST-INITIALIZE/ =================

    // ================ LOAD STRINGS ===================

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    // ================ /LOAD STRINGS/ ===================
}
