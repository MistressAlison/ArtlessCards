package ArtlessCards.patches;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.RenderCardDescriptors;
import basemod.patches.com.megacrit.cardcrawl.screens.SingleCardViewPopup.RenderCardDescriptorsSCV;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

public class ArtlessCardPatches {
    public static final TextureAtlas.AtlasRegion LARGE_COMMON_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Common Attack Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_UNCOMMON_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Uncommon Attack Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_RARE_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Rare Attack Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_COMMON_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Common Skill Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_UNCOMMON_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Uncommon Skill Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_RARE_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Rare Skill Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_COMMON_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Common Power Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_UNCOMMON_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Uncommon Power Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion LARGE_RARE_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/1024/Large Rare Power Frame.png"), 0, 0, 1024, 1024);
    public static final TextureAtlas.AtlasRegion SMALL_COMMON_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Common Attack Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_UNCOMMON_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Uncommon Attack Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_RARE_ATTACK_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Rare Attack Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_COMMON_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Common Skill Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_UNCOMMON_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Uncommon Skill Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_RARE_SKILL_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Rare Skill Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_COMMON_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Common Power Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_UNCOMMON_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Uncommon Power Frame.png"), 0, 0, 512, 512);
    public static final TextureAtlas.AtlasRegion SMALL_RARE_POWER_FRAME = new TextureAtlas.AtlasRegion(new Texture("ArtlessCardsResources/images/512/Small Rare Power Frame.png"), 0, 0, 512, 512);

    @SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
    public static class MakeStatEquivalentCopy {
        public static AbstractCard Postfix(AbstractCard result, AbstractCard self) {
            ArtlessField.isArtless.set(result, ArtlessField.isArtless.get(self));
            return result;
        }
    }

    public static boolean isArtless(AbstractCard c) {
        return ArtlessField.isArtless.get(c);
    }

    public static AtlasRegion getSmallFrame(AbstractCard c) {
        return getSmallFrame(c, c.rarity);
    }

    public static AtlasRegion getSmallFrame(AbstractCard c, AbstractCard.CardRarity r) {
        switch (c.type) {
            case ATTACK:
                switch (r) {
                    default:
                        return SMALL_COMMON_ATTACK_FRAME;
                    case UNCOMMON:
                        return SMALL_UNCOMMON_ATTACK_FRAME;
                    case RARE:
                        return SMALL_RARE_ATTACK_FRAME;
                }
            case POWER:
                switch (r) {
                    default:
                        return SMALL_COMMON_POWER_FRAME;
                    case UNCOMMON:
                        return SMALL_UNCOMMON_POWER_FRAME;
                    case RARE:
                        return SMALL_RARE_POWER_FRAME;
                }
            default:
                switch (r) {
                    default:
                        return SMALL_COMMON_SKILL_FRAME;
                    case UNCOMMON:
                        return SMALL_UNCOMMON_SKILL_FRAME;
                    case RARE:
                        return SMALL_RARE_SKILL_FRAME;
                }
        }
    }

    public static AtlasRegion getLargeFrame(AbstractCard c) {
        return getLargeFrame(c, c.rarity);
    }

    public static AtlasRegion getLargeFrame(AbstractCard c, AbstractCard.CardRarity r) {
        switch (c.type) {
            case ATTACK:
                switch (r) {
                    default:
                        return LARGE_COMMON_ATTACK_FRAME;
                    case UNCOMMON:
                        return LARGE_UNCOMMON_ATTACK_FRAME;
                    case RARE:
                        return LARGE_RARE_ATTACK_FRAME;
                }
            case POWER:
                switch (r) {
                    default:
                        return LARGE_COMMON_POWER_FRAME;
                    case UNCOMMON:
                        return LARGE_UNCOMMON_POWER_FRAME;
                    case RARE:
                        return LARGE_RARE_POWER_FRAME;
                }
            default:
                switch (r) {
                    default:
                        return LARGE_COMMON_SKILL_FRAME;
                    case UNCOMMON:
                        return LARGE_UNCOMMON_SKILL_FRAME;
                    case RARE:
                        return LARGE_RARE_SKILL_FRAME;
                }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderImage")
    private static class NoImagePls {
        static ReflectionHacks.RMethod bg = ReflectionHacks.privateMethod(AbstractCard.class, "renderCardBg", SpriteBatch.class, float.class, float.class);
        static ReflectionHacks.RMethod frame = ReflectionHacks.privateMethod(AbstractCard.class, "renderPortraitFrame", SpriteBatch.class, float.class, float.class);
        static ReflectionHacks.RMethod border = ReflectionHacks.privateMethod(AbstractCard.class, "renderBannerImage", SpriteBatch.class, float.class, float.class);
        @SpirePrefixPatch()
        public static SpireReturn<?> cease(AbstractCard __instance, SpriteBatch sb, boolean hovered, boolean selected) {
            if (isArtless(__instance)) {
                bg.invoke(__instance, sb, __instance.current_x, __instance.current_y);
                frame.invoke(__instance, sb, __instance.current_x, __instance.current_y);
                border.invoke(__instance, sb, __instance.current_x, __instance.current_y);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderAttackPortrait")
    @SpirePatch(clz = AbstractCard.class, method = "renderSkillPortrait")
    @SpirePatch(clz = AbstractCard.class, method = "renderPowerPortrait")
    private static class BigAssFrameTime {
        static ReflectionHacks.RMethod helper = ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper", SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class);
        @SpirePrefixPatch()
        public static SpireReturn<?> zoomin(AbstractCard __instance, SpriteBatch sb, float x, float y, Color ___renderColor) {
            if (isArtless(__instance)) {
                helper.invoke(__instance, sb, ___renderColor, getSmallFrame(__instance), x, y);
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(clz = AbstractCard.class, method = "renderType")
    public static class MoveRenderType {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                //Method call is basically the equivalent of a methodcallmatcher of an insert patch, checks the edit method against every method call in the function you#re patching
                public void edit(MethodCall m) throws CannotCompileException {
                    //If the method is from the class AnimationState and the method is called update
                    if (m.getClassName().equals(FontHelper.class.getName()) && m.getMethodName().equals("renderRotatedText")) {
                        m.replace("{" +
                                //This is usable and refers to the class you#re patching, can be substitued by $0 but that has extra rules
                                "if(ArtlessCards.patches.ArtlessCardPatches.isArtless(this)) {" +
                                //$1 refers to the first input parameter of the method, in this case the float that Gdx.graphics.getDeltaTime() returns
                                "$5 = $5 - (170f * this.drawScale * com.megacrit.cardcrawl.core.Settings.scale);" +
                                "}" +
                                //Call the method as normal
                                "$proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }

    @SpirePatch2(clz = RenderCardDescriptors.FixDynamicFrame.class, method = "Prefix")
    public static class MoveCardDescriptorRender {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                //Method call is basically the equivalent of a methodcallmatcher of an insert patch, checks the edit method against every method call in the function you#re patching
                public void edit(MethodCall m) throws CannotCompileException {
                    //If the method is from the class AnimationState and the method is called update
                    if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw")) {
                        m.replace("{" +
                                //This is usable and refers to the class you#re patching, can be substitued by $0 but that has extra rules
                                "if(ArtlessCards.patches.ArtlessCardPatches.isArtless(__instance)) {" +
                                //$1 refers to the first input parameter of the method, in this case the float that Gdx.graphics.getDeltaTime() returns
                                "$3 = $3 - (170f * __instance.drawScale * com.megacrit.cardcrawl.core.Settings.scale);" +
                                "}" +
                                //Call the method as normal
                                "$proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }

    @SpirePatch(clz= AbstractCard.class, method="renderDescription")
    @SpirePatch(clz= AbstractCard.class, method="renderDescriptionCN")
    public static class RepositionDescription {
        @SpireInsertPatch(locator=Locator.class, localvars={"draw_y"})
        public static void Insert(AbstractCard __instance, SpriteBatch sb, @ByRef float[] draw_y) {
            if (isArtless(__instance)) {
                //draw_y[0] += (Settings.BIG_TEXT_MODE ? 0.24F : 0.255F) * 420.0F * Settings.scale * __instance.drawScale;
                draw_y[0] = __instance.current_y - 20f * Settings.scale * __instance.drawScale;
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "getDescFont");
                int[] hit = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                hit[0] += 2;
                return hit;
            }
        }
    }

    @SpirePatch(clz= SingleCardViewPopup.class, method="renderDescription")
    @SpirePatch(clz= SingleCardViewPopup.class, method="renderDescriptionCN")
    public static class RepositionDescriptionSCV {
        @SpireInsertPatch(locator=LocatorSCV.class, localvars={"draw_y"})
        public static void InsertSCV(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef float[] draw_y) {
            if (isArtless(gimmeCard(__instance))) {
                //draw_y[0] += (Settings.BIG_TEXT_MODE ? 0.24F : 0.255F) * 420.0F * Settings.scale * __instance.drawScale;
                draw_y[0] += 150f * Settings.scale;
            }
        }

        private static class LocatorSCV extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(FontHelper.class, "SCP_cardDescFont");
                int[] hit = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                hit[0] += 2;
                return hit;
            }
        }
    }

    @SpirePatch(clz = CustomCard.class, method = "setDisplayRarity")
    private static class FixSetDisplayRarityShenanigans {
        @SpirePostfixPatch()
        public static void setCorrectFrames(CustomCard __instance, AbstractCard.CardRarity rarity) {
            if (isArtless(__instance)) {
                __instance.frameSmallRegion = getSmallFrame(__instance, rarity);
                __instance.frameLargeRegion = getLargeFrame(__instance, rarity);
            }
        }
    }

    @SpirePatch2(clz = SingleCardViewPopup.class, method = "renderCardTypeText")
    public static class MoveRenderTypeSCV {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                //Method call is basically the equivalent of a methodcallmatcher of an insert patch, checks the edit method against every method call in the function you#re patching
                public void edit(MethodCall m) throws CannotCompileException {
                    //If the method is from the class AnimationState and the method is called update
                    if (m.getClassName().equals(FontHelper.class.getName()) && m.getMethodName().equals("renderFontCentered")) {
                        m.replace("{" +
                                //This is usable and refers to the class you#re patching, can be substitued by $0 but that has extra rules
                                "if(ArtlessCards.patches.ArtlessCardPatches.isArtless(ArtlessCards.patches.ArtlessCardPatches.gimmeCard(this))) {" +
                                //$1 refers to the first input parameter of the method, in this case the float that Gdx.graphics.getDeltaTime() returns
                                "$5 = $5 - (340f * com.megacrit.cardcrawl.core.Settings.scale);" +
                                "}" +
                                //Call the method as normal
                                "$proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }

    @SpirePatch2(clz = RenderCardDescriptorsSCV.FixDynamicFrame.class, method = "Prefix")
    public static class MoveCardDescriptorRenderSCV {
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                //Method call is basically the equivalent of a methodcallmatcher of an insert patch, checks the edit method against every method call in the function you#re patching
                public void edit(MethodCall m) throws CannotCompileException {
                    //If the method is from the class AnimationState and the method is called update
                    if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw")) {
                        m.replace("{" +
                                //This is usable and refers to the class you#re patching, can be substitued by $0 but that has extra rules
                                "if(ArtlessCards.patches.ArtlessCardPatches.isArtless(___card)) {" +
                                //$1 refers to the first input parameter of the method, in this case the float that Gdx.graphics.getDeltaTime() returns
                                "$3 = $3 - (340f * com.megacrit.cardcrawl.core.Settings.scale);" +
                                "}" +
                                //Call the method as normal
                                "$proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }

    public static AbstractCard gimmeCard(SingleCardViewPopup scv) {
        return ReflectionHacks.getPrivate(scv, SingleCardViewPopup.class, "card");
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderPortrait")
    private static class NoImagePlsSCV {
        @SpirePrefixPatch()
        public static SpireReturn<?> cease(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard ___card) {
            if (isArtless(___card)) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz= SingleCardViewPopup.class, method="renderFrame")
    public static class LargeCardFramePatch {
        static ReflectionHacks.RMethod helper = ReflectionHacks.privateMethod(SingleCardViewPopup.class, "renderHelper", SpriteBatch.class, float.class, float.class, TextureAtlas.AtlasRegion.class);
        @SpireInsertPatch(locator=Locator.class, localvars={"tmpImg"})
        public static void Insert(SingleCardViewPopup __instance, SpriteBatch sb, @ByRef TextureAtlas.AtlasRegion[] tmpImg) {
            AbstractCard reflectedCard = ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            if (isArtless(reflectedCard)) {
                renderHelper(__instance, sb, getLargeFrame(reflectedCard));
                tmpImg[0] = getLargeFrame(reflectedCard);
            }
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SingleCardViewPopup.class, "renderHelper");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static void renderHelper(SingleCardViewPopup __instance, SpriteBatch sb, TextureAtlas.AtlasRegion texture) {
            helper.invoke(__instance, sb, Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, texture);
        }
    }
}
