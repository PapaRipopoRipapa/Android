package com.example.bartek.myapplication;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

/**
 * Created by Bartek on 2016-11-01.
 */

public class MusicManagerTestSuite {
    private SharedPreferences sharedPreferencesMock;
    private MusicManager sut;

    @Before
    public void setUp() throws Exception
    {
        sharedPreferencesMock = createStrictMock(SharedPreferences.class);
        sut = new MusicManager();
    }

    @Test
    public void test()
    {
        sharedPreferencesMock.registerOnSharedPreferenceChangeListener(sut);
        expect(sharedPreferencesMock.getBoolean("music_settings", true)).andReturn(true).times(1);
        replay(sharedPreferencesMock);
    }
}
