package com.ontalsoft.efs.exporter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public enum GfxFileSpecs {

    // BIN ====================================================================

    BUTTON_GALAXY(Dir.BIN, "EFSBUT2", 23, 23, 3, Type.BIN, 1, false, null, "Galaxy button", 1_578L),
    BUTTON_LAUNCH(Dir.BIN, "EFSBUT3", 23, 41, 4, Type.BIN, 1, false, null, "Launch button", 3_772L),
    BUTTON_GROUPALL(Dir.BIN, "EFSBUT4", 30, 14, 4, Type.BIN, 1, false, null, "Group all button", 1_680L),
    BUTTON_ARROW_RIGHT(Dir.BIN, "EFSBUT5", 41, 14, 4, Type.BIN, 1, false, null, "Right arrow button", 2_296L),
    BUTTON_VIEW_STACK(Dir.BIN, "EFSBUT6", 30, 14, 4, Type.BIN, 1, false, null, "View stack button", 1_680L),
    BUTTON_TRAIT(Dir.BIN, "EFSBUT7", 25, 25, 2, Type.BIN, 1, false, null, "Select trait button", 1_250L),
    BUTTON_SENTRY(Dir.BIN, "EFSBUT8", 25, 25, 4, Type.BIN, 1, false, null, "Sentry button", 2_500L),
    BUTTON_NEXT_UNIT(Dir.BIN, "EFSBUT9", 25, 25, 4, Type.BIN, 1, false, null, "Next unit button", 2_500L),
    BUTTON_SKIP_UNIT(Dir.BIN, "EFSBUT10", 25, 25, 4, Type.BIN, 1, false, null, "Skip unit button", 2_500L),
    BUTTON_MOVE(Dir.BIN, "EFSBUT11", 25, 25, 4, Type.BIN, 1, false, null, "Move unit button", 2_500L),
    BUTTON_END_TURN(Dir.BIN, "EFSBUT16", 54, 50, 3, Type.BIN, 1, false, null, "End turn button", 8_100L),
    BUTTON_SCROLL(Dir.BIN, "SCROLL", 16, 16, 5, Type.BIN, 1, false, null, "Scroll bar elements", 1_280L),

    ICON_RESOURCES(Dir.BIN, "CARGO", 34, 29, 13, Type.BIN, 13, false, null, "Resources icons", 12_818L),
    ICON_COUPLING(Dir.BIN, "COUPLE", 20, 31, 1, Type.BIN, 1, false, null, "Cargo coupling", 620L),
    ICON_RELATION(Dir.BIN, "EFSBUT1", 27, 27, 2, Type.BIN, 1, false, null, "Faction relation icon", 1_458L),
    ICON_VETERANCY(Dir.BIN, "EFSBUT12", 23, 12, 2, Type.BIN, 1, false, null, "Veternacy icons", 552L),
    ICON_LOYALTY(Dir.BIN, "EFSBUT13", 15, 13, 2, Type.BIN, 1, false, null, "Loyalty icon", 390L),
    ICON_THUMB(Dir.BIN, "EFSBUT14", 27, 27, 2, Type.BIN, 1, false, null, "Thumb up/down icon", 1_458L),
    ICON_LIFE(Dir.BIN, "EFSBUT15", 15, 13, 5, Type.BIN, 1, false, null, "Life icon", 975L),
    ICON_FIST(Dir.BIN, "FIST", 32, 32, 1, Type.BIN, 1, false, null, "Fist icon", 1_024L),
    ICON_SURRENDER(Dir.BIN, "FLAG", 40, 40, 1, Type.BIN, 1, false, null, "Surrender flag icon", 1_600L),
    ICON_DEATH(Dir.BIN, "SKULL", 40, 40, 1, Type.BIN, 1, false, null, "Skull icon", 1_600L),
    ICON_PLAYER_COMPUTER(Dir.BIN, "PLYCOMP", 48, 64, 1, Type.BIN, 1, false, null, "Computer player icon", 3_072L),
    ICON_PLAYER_HUMAN(Dir.BIN, "PLYHUMAN", 48, 64, 1, Type.BIN, 1, false, null, "Human player icon", 3_072L),

    ICON_UNITS(Dir.BIN, "EFSUNIT", 32, 32, 92, Type.BIN, 16, false, null, "Units icons", 94_208L),
    ICON_POD(Dir.BIN, "POD", 24, 9, 1, Type.BIN, 1, false, null, "Cargo pod icon", 216L),

    BANNER_VACANT(Dir.BIN, "BANNERS", 120, 123, 1, Type.BIN, 1, false, null, "Vacant regent banner", 14_760L),
    BANNER_CHURCH(Dir.BIN, "BNRCHU", 100, 100, 1, Type.BIN, 1, false, null, "Church banner", 10_000L),
    BANNER_LEAGUE(Dir.BIN, "BNRLEG", 100, 100, 1, Type.BIN, 1, false, null, "League banner", 10_000L),
    BANNER_REBELS(Dir.BIN, "BNRREB", 100, 100, 1, Type.BIN, 1, false, null, "Rebels banner", 10_000L),
    BANNER_VAU(Dir.BIN, "BNRVAU", 100, 100, 1, Type.BIN, 1, false, null, "Vau banner", 10_000L),
    BANNER_EYE(Dir.BIN, "EYE100", 100, 100, 1, Type.BIN, 1, false, null, "Imperial Eye banner", 10_000L),
    BANNER_FLEET(Dir.BIN, "FLET100", 100, 100, 1, Type.BIN, 1, false, null, "Imperial Fleet banner", 10_000L),
    BANNER_REGENT(Dir.BIN, "RGT100", 100, 100, 1, Type.BIN, 1, false, null, "Regent banner", 10_000L),
    BANNER_STIGMATA(Dir.BIN, "STIG100", 100, 100, 1, Type.BIN, 1, false, null, "Stigmata banner", 10_000L),
    BANNER_SYMBIOTS(Dir.BIN, "SYM100", 100, 100, 1, Type.BIN, 1, false, null, "Symbiots banner", 10_000L),
    BANNER_LIHALAN(Dir.BIN, "HOUSE1", 100, 100, 1, Type.BIN, 1, false, null, "House Li Halan banner", 10_000L),
    BANNER_HAZAT(Dir.BIN, "HOUSE2", 100, 100, 1, Type.BIN, 1, false, null, "House Hazat banner", 10_000L),
    BANNER_DECADOS(Dir.BIN, "HOUSE3", 100, 100, 1, Type.BIN, 1, false, null, "House Decados banner", 10_000L),
    BANNER_HAWKWOOD(Dir.BIN, "HOUSE4", 100, 100, 1, Type.BIN, 1, false, null, "House Hawkwood banner", 10_000L),
    BANNER_ALMALIK(Dir.BIN, "HOUSE5", 100, 100, 1, Type.BIN, 1, false, null, "House Al-Malik banner", 10_000L),

    BANNER_EYE_HOUSES(Dir.BIN, "EYE", 120, 123, 6, Type.BIN, 3, false, null, "Imperial Eye houses banners", 88_560L),
    BANNER_FLEET_HOUSES(Dir.BIN, "FLEET", 120, 123, 6, Type.BIN, 3, false, null, "Imperial Fleet houses banners", 88_560L),
    BANNER_REGENT_HOUSES(Dir.BIN, "REGENT", 120, 123, 6, Type.BIN, 3, false, null, "Regent houses banners", 88_560L),
    BANNER_STIGMATA_HOUSES(Dir.BIN, "STIGMATA", 120, 123, 6, Type.BIN, 3, false, null, "Stigmata houses banners", 88_560L),

    IMG_PORTRAIT_HOUSES(Dir.BIN, "PORTRAIT", 250, 200, 15, Type.BIN, 5, false, null, "Nobles portraits", 750_000L),
    IMG_PORTRAIT_CHURCH(Dir.BIN, "PTRATCHU", 250, 200, 5, Type.BIN, 5, false, null, "Church portraits", 250_000L),
    IMG_PORTRAIT_LEAGUE(Dir.BIN, "PTRATLEA", 250, 200, 1, Type.BIN, 1, false, null, "League portrait", 50_000L),
    IMG_PORTRAIT_TUTOR(Dir.BIN, "PTRATTUT", 250, 200, 1, Type.BIN, 1, false, null, "Tutor portrait", 50_000L),
    IMG_PORTRAIT_VAU(Dir.BIN, "PTRATVAU", 250, 200, 1, Type.BIN, 1, false, null, "Vau portrait", 50_000L),

    ANIM_PLANETS(Dir.BIN, "EFSPLAN", 32, 32, 128, Type.BIN, 16, false, null, "Planets animations", 131_072L),
    ANIM_EXPLOSION(Dir.BIN, "FIRE", 29, 32, 4, Type.BIN, 4, false, null, "Explosion animation", 3_712L),
    ANIM_JUMP(Dir.BIN, "JUMP", 34, 34, 7, Type.BIN, 7, false, null, "Jump animation", 8_092L),

    TERRAIN_TEMPERATE(Dir.BIN, "EFSTILE0", 48, 40, 135, Type.BIN, 16, true, 1520, "Temperate terrain tiles", 203_680L),
    TERRAIN_MEGACITY(Dir.BIN, "EFSTILE1", 48, 40, 134, Type.BIN, 16, true, 1520, "Megacity terrain tiles", 203_680L),
    TERRAIN_ARCTIC(Dir.BIN, "EFSTILE2", 48, 40, 134, Type.BIN, 16, true, 1520, "Arctic terrain tiles", 203_680L),
    TERRAIN_JUNGLE(Dir.BIN, "EFSTILE3", 48, 40, 134, Type.BIN, 16, true, 1520, "Jungle terrain tiles", 203_680L),
    TERRAIN_VOLCANIC(Dir.BIN, "EFSTILE4", 48, 40, 134, Type.BIN, 16, true, 1520, "Volcanic terrain tiles", 203_680L),

    TERRAIN_EXPLORE(Dir.BIN, "EXPLORE", 48, 40, 11, Type.BIN, 1, true, 1520, "Fog of war, movement path visuals", 16_720L),
    TERRAIN_SHIELD_EFFECT(Dir.BIN, "GLIMMER", 48, 40, 1, Type.BIN, 1, true, 1520, "Shield glimmer effect", 1_520L),

    BUILDINGS_TEMPERATE(Dir.BIN, "STRUCT0", 48, 40, 32, Type.BIN, 16, true, 1520, "Temperate buildings icons", 48_640L),
    BUILDINGS_MEGACITY(Dir.BIN, "STRUCT1", 48, 40, 32, Type.BIN, 16, true, 1520, "Megacity buildings icons", 48_640L),
    BUILDINGS_ARCTIC(Dir.BIN, "STRUCT2", 48, 40, 32, Type.BIN, 16, true, 1520, "Arctic buildings icons", 48_640L),
    BUILDINGS_JUNGLE(Dir.BIN, "STRUCT3", 48, 40, 32, Type.BIN, 16, true, 1520, "Jungle buildings icons", 48_640L),
    BUILDINGS_VOLCANIC(Dir.BIN, "STRUCT4", 48, 40, 32, Type.BIN, 16, true, 1520, "Volcanic buildings icons", 48_640L),

    //    X_BORDER(Dir.BIN, "BORDER", 0, 0, 1, Type.BIN, 1, false, null, "??? BORDER", 645L),
    //    X_S_BORD(Dir.BIN, "S_BORD", 0, 0, 1, Type.BIN, 1, false, null, "??? S_BORD", 645L),
    //    X_SHIELD(Dir.BIN, "SHIELD", 0, 0, 1, Type.BIN, 1, false, null, "??? SHIELD", 30L),
    //    X_V_BORD(Dir.BIN, "V_BORD", 0, 0, 1, Type.BIN, 1, false, null, "??? V_BORD", 86L),

    // MANOWITZ ===============================================================

    BUTTON_CLOSE(Dir.MANOWITZ, "CLOSE", 81, 25, 4, Type.BIN, 1, false, null, "Close button", 8_100L),
    BUTTON_CONTENTS(Dir.MANOWITZ, "CONTENTS", 73, 25, 4, Type.BIN, 1, false, null, "Contents button", 7_300L),
    BUTTON_NEXT(Dir.MANOWITZ, "NEXT", 89, 41, 4, Type.BIN, 1, false, null, "Next button", 14_596L),
    BUTTON_PREV(Dir.MANOWITZ, "PREV", 81, 41, 4, Type.BIN, 1, false, null, "Previous button", 13_284L),

    IMG_BOOK01(Dir.MANOWITZ, "BKIL01", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 1", 11_271L),
    IMG_BOOK02(Dir.MANOWITZ, "BKIL02", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 2", 13_822L),
    IMG_BOOK03(Dir.MANOWITZ, "BKIL03", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 3", 10_893L),
    IMG_BOOK04(Dir.MANOWITZ, "BKIL04", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 4", 12_638L),
    IMG_BOOK05(Dir.MANOWITZ, "BKIL05", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 5", 15_115L),
    IMG_BOOK06(Dir.MANOWITZ, "BKIL06", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 6", 12_889L),
    IMG_BOOK08(Dir.MANOWITZ, "BKIL08", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 8", 13_037L),
    IMG_BOOK09(Dir.MANOWITZ, "BKIL09", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 9", 15_868L),
    IMG_BOOK10(Dir.MANOWITZ, "BKIL10", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 10", 18_398L),
    IMG_BOOK11(Dir.MANOWITZ, "BKIL11", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 11", 17_155L),
    IMG_BOOK12(Dir.MANOWITZ, "BKIL12", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 12", 15_085L),
    IMG_BOOK13(Dir.MANOWITZ, "BKIL13", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 13", 12_962L),
    IMG_BOOK14(Dir.MANOWITZ, "BKIL14", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 14", 12_608L),
    IMG_BOOK15(Dir.MANOWITZ, "BKIL15", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 15", 13_737L),
    IMG_BOOK16(Dir.MANOWITZ, "BKIL16", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 16", 16_527L),
    IMG_BOOK17(Dir.MANOWITZ, "BKIL17", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 17", 10_853L),
    IMG_BOOK18(Dir.MANOWITZ, "BKIL18", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 18", 14_542L),
    IMG_BOOK19(Dir.MANOWITZ, "BKIL19", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 19", 11_014L),
    IMG_BOOK20(Dir.MANOWITZ, "BKIL20", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 20", 15_122L),
    IMG_BOOK21(Dir.MANOWITZ, "BKIL21", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 21", 13_709L),
    IMG_BOOK22(Dir.MANOWITZ, "BKIL22", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 22", 11_918L),
    IMG_BOOK23(Dir.MANOWITZ, "BKIL23", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 23", 12_948L),
    IMG_BOOK24(Dir.MANOWITZ, "BKIL24", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 24", 5_520L),
    IMG_BOOK25(Dir.MANOWITZ, "BKIL25", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 25", 16_603L),
    IMG_BOOK26(Dir.MANOWITZ, "BKIL26", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 26", 17_332L),
    IMG_BOOK27(Dir.MANOWITZ, "BKIL27", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 27", 12_268L),
    IMG_BOOK28(Dir.MANOWITZ, "BKIL28", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 28", 11_411L),
    IMG_BOOK29(Dir.MANOWITZ, "BKIL29", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 29", 139_129L),
    IMG_BOOK30(Dir.MANOWITZ, "BOOK5H", 640, 480, 1, Type.PCX, 1, false, null, "Encyclopedia image 30", 11_271L),
    IMG_BOOK31(Dir.MANOWITZ, "PAL", 195, 167, 1, Type.PCX, 1, false, null, "Encyclopedia image 31", 16_477L),

    // PCX ====================================================================

    GUI_FRAME_GENERIC(Dir.PCX, "AIPLAT", 640, 480, 1, Type.PCX, 1, false, null, "Generic view main GUI frame", 47_345L),
    GUI_BACKGROUND1(Dir.PCX, "BG0", 640, 480, 1, Type.PCX, 1, false, null, "Background 1", 264_982L),
    GUI_BACKGROUND2(Dir.PCX, "BG1", 640, 480, 1, Type.PCX, 1, false, null, "Background 2", 247_235L),
    GUI_THRONE(Dir.PCX, "BYZSECU", 640, 480, 1, Type.PCX, 1, false, null, "Throne room", 141_183L),
    GUI_MAIN_MENU_BG(Dir.PCX, "CATHED3", 640, 480, 1, Type.PCX, 1, false, null, "Main menu background", 274_862L),
    GUI_CONTRACT(Dir.PCX, "CONTRACT", 640, 480, 1, Type.PCX, 1, false, null, "Contract screen", 182_379L),
    GUI_DIPLOMACY_CHURCH(Dir.PCX, "DIPLOCHU", 640, 480, 1, Type.PCX, 1, false, null, "Curch diplomacy screen", 172_368L),
    GUI_DIPLOMACY_LEAGUE(Dir.PCX, "DIPLOLEG", 640, 480, 1, Type.PCX, 1, false, null, "League diplomacy screen", 182_663L),
    GUI_DIPLOMACY(Dir.PCX, "DIPLOMA", 640, 480, 1, Type.PCX, 1, false, null, "House diplomacy screen", 179_440L),
    GUI_EVENT_ASSASINATION1(Dir.PCX, "EVASSA", 640, 480, 1, Type.PCX, 1, false, null, "Assasination event type 1 image", 226_787L),
    GUI_EVENT_ASSASINATION2(Dir.PCX, "EVASSB", 640, 480, 1, Type.PCX, 1, false, null, "Assasination event type 2 image", 199_258L),
    GUI_EVENT_ASSASINATION3(Dir.PCX, "EVASSC", 640, 480, 1, Type.PCX, 1, false, null, "Assasination event type 3 image", 214_124L),
    GUI_EVENT_FAMINE(Dir.PCX, "EVFAMN", 640, 480, 1, Type.PCX, 1, false, null, "Famine event image", 208_125L),
    GUI_EVENT_INQUISITION(Dir.PCX, "EVINQUI", 640, 480, 1, Type.PCX, 1, false, null, "Inquisition event image", 240_945L),
    GUI_EVENT_LABS(Dir.PCX, "EVLAB2", 640, 480, 1, Type.PCX, 1, false, null, "Burning labs event image", 205_123L),
    GUI_EVENT_3RDREP(Dir.PCX, "EVLEAG", 640, 480, 1, Type.PCX, 1, false, null, "3rd Republic event image", 261_063L),
    GUI_EVENT_PLAGUE(Dir.PCX, "EVPLAG", 640, 480, 1, Type.PCX, 1, false, null, "Plague event image", 196_357L),
    GUI_EVENT_REBEION(Dir.PCX, "EVREBEL", 640, 480, 1, Type.PCX, 1, false, null, "Rebelion event image", 221_674L),
    GUI_EVENT_SYMBIOT_MESSAGE(Dir.PCX, "EVSYM3", 640, 480, 1, Type.PCX, 1, false, null, "Symbiot message event image", 236_137L),
    GUI_EVENT_SYMBIOT_BATTLE(Dir.PCX, "EVSYMAT", 640, 480, 1, Type.PCX, 1, false, null, "Symbiot battle event image", 204_744L),
    GUI_EVENT_HOLYWRIT(Dir.PCX, "EVWRIT1", 640, 480, 1, Type.PCX, 1, false, null, "Holy Writ event image", 165_775L),
    GUI_CREDITS(Dir.PCX, "FSART0", 640, 480, 1, Type.PCX, 1, false, null, "Game promo image", 193_459L),
    GUI_HOUSE_INFO(Dir.PCX, "HOUSE", 640, 480, 1, Type.PCX, 1, false, null, "House info screen", 199_684L),
    GUI_END(Dir.PCX, "LOSE_BCK", 640, 480, 1, Type.PCX, 1, false, null, "End game screen", 190_783L),
    GUI_EDITOR_MAIN(Dir.PCX, "MAINBORD", 640, 480, 1, Type.PCX, 1, false, null, "Editor main GUI frame", 96_226L),
    GUI_FRAME_PLANET(Dir.PCX, "PLNPLAT3", 640, 480, 1, Type.PCX, 1, false, null, "Planet view main GUI frame", 57_875L),
    GUI_STARFIELD(Dir.PCX, "STARFLD2", 640, 480, 1, Type.PCX, 1, false, null, "Starfield galaxy background", 8_580L),
    GUI_EDITOR_GALAXY(Dir.PCX, "STARMAP", 640, 480, 1, Type.PCX, 1, false, null, "Editor galaxy view main GUI frame", 53_185L),
    GUI_FRAME_GALAXY(Dir.PCX, "STARMAP3", 640, 480, 1, Type.PCX, 1, false, null, "Galaxy view main GUI frame", 45_643L),
    GUI_FRAME_UNIT(Dir.PCX, "UNITBG2", 640, 480, 1, Type.PCX, 1, false, null, "Unit build screen", 48_807L),
    GUI_FRAME_STACK(Dir.PCX, "UNITINFO", 640, 480, 1, Type.PCX, 1, false, null, "Unit info screen", 204_433L),
    GUI_FRAME_VOTE(Dir.PCX, "VOTE", 640, 480, 1, Type.PCX, 1, false, null, "Voting screen", 63_477L);

    private static final Map<Dir, Map<String, GfxFileSpecs>> lookup = new HashMap<>();

    private Dir dir;
    private String name;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;
    private Type type;
    private int outputWidthInFrames;
    private boolean isHexagonal;
    private Integer hexagonPixelsLength;
    private String description;
    private long fileLegth;

    private GfxFileSpecs(
            final Dir dir,
            final String name,
            final int frameWidth,
            final int frameHeight,
            final int frameCount,
            final Type type,
            final int outputWidthInFrames,
            final boolean isHexagonal,
            final Integer hexagonPixelsLength,
            final String description,
            final long fileLegth) {

        this.dir = dir;
        this.name = name;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = frameCount;
        this.type = type;
        this.outputWidthInFrames = outputWidthInFrames;
        this.isHexagonal = isHexagonal;
        this.hexagonPixelsLength = hexagonPixelsLength;
        this.description = description;
        this.fileLegth = fileLegth;
    }

    static {
        for(final GfxFileSpecs t : EnumSet.allOf(GfxFileSpecs.class)) {
            if( !lookup.containsKey(t.getDir())) {
                lookup.put(t.getDir(), new HashMap<>());
            }
            lookup.get(t.getDir()).put(t.getName(), t);
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    public static GfxFileSpecs getByDirAndName(final String dir, final String name) {
        try {
            return lookup.get(dir).get(name);
        }
        catch(final Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static enum Type {
        BIN,
        PCX;
    }

    public static enum Dir {
        BIN,
        MANOWITZ,
        PCX;
    }
}
