/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.summer.sdt.internal.ui.preferences.cleanup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.summer.sdt.internal.corext.fix.CleanUpConstants;
import org.summer.sdt.internal.ui.JavaPlugin;
import org.summer.sdt.internal.ui.preferences.PreferencesAccess;
import org.summer.sdt.internal.ui.preferences.formatter.IProfileVersioner;
import org.summer.sdt.internal.ui.preferences.formatter.ProfileManager;
import org.summer.sdt.ui.JavaUI;

public class CleanUpProfileManager extends ProfileManager {

	public static KeySet[] KEY_SETS= {
		new KeySet(JavaUI.ID_PLUGIN, new ArrayList<String>(JavaPlugin.getDefault().getCleanUpRegistry().getDefaultOptions(CleanUpConstants.DEFAULT_CLEAN_UP_OPTIONS).getKeys()))
	};

	private final PreferencesAccess fPreferencesAccess;

	public CleanUpProfileManager(List<Profile> profiles, IScopeContext context, PreferencesAccess preferencesAccess, IProfileVersioner profileVersioner) {
	    super(profiles, context, preferencesAccess, profileVersioner, KEY_SETS, CleanUpConstants.CLEANUP_PROFILE, CleanUpConstants.CLEANUP_SETTINGS_VERSION_KEY);
		fPreferencesAccess= preferencesAccess;
    }

	/* (non-Javadoc)
     * @see org.summer.sdt.internal.ui.preferences.cleanup.ProfileManager#getDefaultProfile()
     */
    @Override
	public Profile getDefaultProfile() {
    	return getProfile(CleanUpConstants.DEFAULT_PROFILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
	protected void updateProfilesWithName(String oldName, Profile newProfile, boolean applySettings) {
        super.updateProfilesWithName(oldName, newProfile, applySettings);

        IEclipsePreferences node= fPreferencesAccess.getInstanceScope().getNode(JavaUI.ID_PLUGIN);
        String name= node.get(CleanUpConstants.CLEANUP_ON_SAVE_PROFILE, null);
        if (name != null && name.equals(oldName)) {
        	if (newProfile == null) {
        		node.remove(CleanUpConstants.CLEANUP_ON_SAVE_PROFILE);
        	} else {
    			node.put(CleanUpConstants.CLEANUP_ON_SAVE_PROFILE, newProfile.getID());
        	}
    	}
    }

}
