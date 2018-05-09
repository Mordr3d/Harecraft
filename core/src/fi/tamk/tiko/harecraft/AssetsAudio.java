package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AssetsAudio {
    static Music music_course_0;
    static Music music_course_1;
    static Music music_course_2;
    private static Music music_fanfaar_1;
    private static Music music_fanfaar_2;
    private static Music music_fanfaar_3;
    private static Music music_fanfaar_4;
    private static Music music_fanfaar_5;
    private static Music music_fanfaar_6;

    private static Sound sound_cloud_hit;
    private static Sound sound_ring_collected;
    private static Sound sound_balloon_collected;
    private static Sound sound_countdown;
    private static Sound sound_countdown_end;
    private static Sound sound_airplane_engine;
    private static Sound sound_overtaking;
    private static Sound sound_undertaking;
    private static Sound sound_points_counting;
    private static Sound sound_points_counting_end;
    private static Sound sound_points_highscore;

    private static long ID_sound_cloud_hit;
    private static long ID_sound_ring_collected;
    private static long ID_sound_balloon_collected;
    private static long ID_sound_countdown;
    private static long ID_sound_countdown_end;
    private static long ID_sound_airplane_engine;
    private static long ID_sound_overtaking;
    private static long ID_sound_undertaking;
    private static long ID_sound_points_counting;
    private static long ID_sound_points_counting_end;
    private static long ID_sound_points_highscore;

    static final int MUSIC_COURSE_0 = 0;
    static final int MUSIC_COURSE_1 = 1;
    static final int MUSIC_COURSE_2 = 2;
    static final int MUSIC_FANFAAR_1 = 3;
    static final int MUSIC_FANFAAR_2 = 4;
    static final int MUSIC_FANFAAR_3 = 5;
    static final int MUSIC_FANFAAR_4 = 6;
    static final int MUSIC_FANFAAR_5 = 7;
    static final int MUSIC_FANFAAR_6 = 8;
    static final int SOUND_CLOUD_HIT = 9;
    static final int SOUND_RING_COLLECTED = 10;
    static final int SOUND_BALLOON_COLLECTED = 11;
    static final int SOUND_COUNTDOWN = 12;
    static final int SOUND_COUNTDOWN_END = 13;
    static final int SOUND_AIRPLANE_ENGINE = 14;
    static final int SOUND_OVERTAKING = 15;
    static final int SOUND_UNDERTAKING = 16;
    static final int SOUND_POINTS_COUNTING = 17;
    static final int SOUND_POINTS_COUNTING_END = 18;
    static final int SOUND_POINTS_HIGHSCORE = 19;

    private static int CURRENT_MUSIC;

    public static boolean load() {
        music_course_0 = loadMusic("music_course_0.mp3");
        music_course_1 = loadMusic("music_course_1.mp3");
        music_course_2 = loadMusic("music_course_2.mp3");
        music_course_0.setLooping(true);
        music_course_1.setLooping(true);
        music_course_2.setLooping(true);
        music_fanfaar_1 = loadMusic("music_fanfaar_1.wav");
        music_fanfaar_2 = loadMusic("music_fanfaar_2.wav");
        music_fanfaar_3 = loadMusic("music_fanfaar_3.wav");
        music_fanfaar_4 = loadMusic("music_fanfaar_4.wav");
        music_fanfaar_5 = loadMusic("music_fanfaar_5.wav");
        music_fanfaar_6 = loadMusic("music_fanfaar_6.wav");

        sound_cloud_hit = loadSound("sound_cloud_hit.wav");
        sound_ring_collected = loadSound("sound_ring_collected.wav");
        sound_balloon_collected = loadSound("sound_balloon_collected.wav");
        sound_countdown = loadSound("sound_countdown.wav");
        sound_countdown_end = loadSound("sound_countdown_end.wav");
        sound_airplane_engine = loadSound("sound_airplane_engine.wav");
        sound_overtaking = loadSound("sound_overtaking.wav");
        sound_undertaking = loadSound("sound_undertaking.wav");
        sound_points_counting = loadSound("sound_points_counting.wav");
        sound_points_counting_end = loadSound("sound_points_counting_end.wav");
        sound_points_highscore = loadSound("sound_points_highscore.wav");

        ID_sound_cloud_hit = sound_cloud_hit.play(0);
        ID_sound_ring_collected = sound_ring_collected.play(0);
        ID_sound_balloon_collected = sound_balloon_collected.play(0);
        ID_sound_countdown = sound_countdown.play(0);
        ID_sound_countdown_end = sound_countdown_end.play(0);
        ID_sound_airplane_engine = sound_airplane_engine.play(0);
        ID_sound_overtaking = sound_overtaking.play(0);
        ID_sound_undertaking = sound_undertaking.play(0);
        ID_sound_points_counting = sound_points_counting.play(0);
        ID_sound_points_counting_end = sound_points_counting_end.play(0);
        ID_sound_points_highscore = sound_points_highscore.play(0);

        return true;
    }

    public static boolean isPlaying(int musicID) {
        switch (musicID) {
            case MUSIC_FANFAAR_1:
                return music_fanfaar_1.isPlaying();
            case MUSIC_FANFAAR_2:
                return music_fanfaar_2.isPlaying();
            case MUSIC_FANFAAR_3:
                return music_fanfaar_3.isPlaying();
            case MUSIC_FANFAAR_4:
                return music_fanfaar_4.isPlaying();
            case MUSIC_FANFAAR_5:
                return music_fanfaar_5.isPlaying();
            case MUSIC_FANFAAR_6:
                return music_fanfaar_6.isPlaying();
            default:
                return false;
        }
    }

    public static boolean isPlaying() {
        switch (CURRENT_MUSIC) {
            case MUSIC_COURSE_0:
                return music_course_0.isPlaying();
            case MUSIC_COURSE_1:
                return music_course_1.isPlaying();
            case MUSIC_COURSE_2:
                return music_course_2.isPlaying();
            default:
                return false;
        }
    }

    public static void playMusic(int musicID) {
        switch (musicID) {
            case MUSIC_COURSE_0:
                CURRENT_MUSIC = MUSIC_COURSE_0;
                music_course_0.setLooping(true);
                music_course_0.setVolume(0f);
                music_course_0.play();
                break;
            case MUSIC_COURSE_1:
                CURRENT_MUSIC = MUSIC_COURSE_1;
                music_course_1.setLooping(true);
                music_course_2.setVolume(0f);
                music_course_1.play();
                break;
            case MUSIC_COURSE_2:
                CURRENT_MUSIC = MUSIC_COURSE_2;
                music_course_2.setLooping(true);
                music_course_2.setVolume(0f);
                music_course_2.play();
                break;
            case MUSIC_FANFAAR_1:
                music_fanfaar_1.setVolume(0.45f);
                music_fanfaar_1.play();
                break;
            case MUSIC_FANFAAR_2:
                music_fanfaar_2.setVolume(0.45f);
                music_fanfaar_2.play();
                break;
            case MUSIC_FANFAAR_3:
                music_fanfaar_3.setVolume(0.45f);
                music_fanfaar_3.play();
                break;
            case MUSIC_FANFAAR_4:
                music_fanfaar_4.setVolume(0.45f);
                music_fanfaar_4.play();
                break;
            case MUSIC_FANFAAR_5:
                music_fanfaar_5.setVolume(0.45f);
                music_fanfaar_5.play();
                break;
            case MUSIC_FANFAAR_6:
                music_fanfaar_6.setVolume(0.45f);
                music_fanfaar_6.play();
                break;
        }
    }

    public static void setMusicVolume(float volume) {
        switch (CURRENT_MUSIC) {
            case MUSIC_COURSE_0:
                music_course_0.setVolume(volume);
                break;
            case MUSIC_COURSE_1:
                music_course_1.setVolume(volume);
                break;
            case MUSIC_COURSE_2:
                music_course_2.setVolume(volume);
                break;
        }
    }

    public static void stopMusic() {
        switch (CURRENT_MUSIC) {
            case MUSIC_COURSE_0:
                music_course_0.stop();
                break;
            case MUSIC_COURSE_1:
                music_course_1.stop();
                break;
            case MUSIC_COURSE_2:
                music_course_2.stop();
                break;
        }
    }

    public static void stopSound(int soundID) {
        switch (soundID) {
            case SOUND_AIRPLANE_ENGINE:
                sound_airplane_engine.stop(ID_sound_airplane_engine);
                break;
        }
    }

    public static void pauseSound(int soundID) {
        switch (soundID) {
            case SOUND_AIRPLANE_ENGINE:
                sound_airplane_engine.pause(ID_sound_airplane_engine);
                break;
        }
    }

    public static void pauseMusic() {
        switch (CURRENT_MUSIC) {
            case MUSIC_COURSE_0:
                music_course_0.pause();
                break;
            case MUSIC_COURSE_1:
                music_course_1.pause();
                break;
            case MUSIC_COURSE_2:
                music_course_2.pause();
                break;
        }
    }

    public static void resumeMusic() {
        switch (CURRENT_MUSIC) {
            case MUSIC_COURSE_0:
                music_course_0.play();
                break;
            case MUSIC_COURSE_1:
                music_course_1.play();
                break;
            case MUSIC_COURSE_2:
                music_course_2.play();
                break;
        }
    }

    public static void resumeSound(int soundID) {
        switch (soundID) {
            case SOUND_AIRPLANE_ENGINE:
                sound_airplane_engine.resume(ID_sound_airplane_engine);
                break;
        }
    }

    public static void playSound(int soundID, float volume) {
        switch (soundID) {
            case SOUND_CLOUD_HIT:
                sound_cloud_hit.stop(ID_sound_cloud_hit);
                ID_sound_cloud_hit = sound_cloud_hit.play(volume);
                break;
            case SOUND_RING_COLLECTED:
                sound_ring_collected.stop(ID_sound_ring_collected);
                ID_sound_ring_collected = sound_ring_collected.play(volume);
                break;
            case SOUND_BALLOON_COLLECTED:
                sound_balloon_collected.stop(ID_sound_balloon_collected);
                ID_sound_balloon_collected = sound_balloon_collected.play(volume);
                break;
            case SOUND_COUNTDOWN:
                sound_countdown.stop(ID_sound_countdown);
                ID_sound_countdown = sound_countdown.play(volume);
                break;
            case SOUND_COUNTDOWN_END:
                sound_countdown_end.stop(ID_sound_countdown_end);
                ID_sound_countdown_end = sound_countdown_end.play(volume);
                break;
            case SOUND_AIRPLANE_ENGINE:
                sound_airplane_engine.stop(ID_sound_airplane_engine);
                ID_sound_airplane_engine = sound_airplane_engine.loop(volume);
                //sound_airplane_engine.setLooping(ID_sound_airplane_engine,true);
                break;
            case SOUND_OVERTAKING:
                sound_overtaking.stop(ID_sound_overtaking);
                ID_sound_overtaking = sound_overtaking.play(volume);
                break;
            case SOUND_UNDERTAKING:
                sound_undertaking.stop(ID_sound_undertaking);
                ID_sound_undertaking = sound_undertaking.play(volume);
                break;
            case SOUND_POINTS_COUNTING:
                sound_points_counting.stop(ID_sound_points_counting);
                ID_sound_points_counting = sound_points_counting.loop(volume);
                break;
            case SOUND_POINTS_COUNTING_END:
                sound_points_counting_end.stop(ID_sound_points_counting_end);
                ID_sound_points_counting_end = sound_points_counting_end.play(volume);
                break;
            case SOUND_POINTS_HIGHSCORE:
                sound_points_highscore.stop(ID_sound_points_highscore);
                ID_sound_points_highscore = sound_points_highscore.play(volume);
                break;
        }
    }

    public static Sound loadSound(String path) {return Gdx.audio.newSound(Gdx.files.internal("audio/" + path));}

    public static Music loadMusic(String path) {return Gdx.audio.newMusic(Gdx.files.internal("audio/" + path));}

    public static void dispose() {
        music_course_0.dispose();
        music_course_1.dispose();
        music_course_2.dispose();
        music_fanfaar_1.dispose();
        music_fanfaar_2.dispose();
        music_fanfaar_3.dispose();
        music_fanfaar_4.dispose();
        music_fanfaar_5.dispose();
        music_fanfaar_6.dispose();

        sound_overtaking.dispose();
        sound_undertaking.dispose();
        sound_cloud_hit.dispose();
        sound_ring_collected.dispose();
        sound_countdown.dispose();
        sound_countdown_end.dispose();
        sound_airplane_engine.dispose();
        sound_balloon_collected.dispose();
        sound_points_counting.dispose();
        sound_points_counting_end.dispose();
        sound_points_highscore.dispose();
    }
}
