package com.marcohc.helperoid;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreferencesHelperTest {

    @Mock
    Context mMockContext;

    @Test(expected = PreferencesHelper.PreferencesHelperException.class)
    public void testGetInstanceThrowsException() {
        PreferencesHelper.getInstance();
    }

}