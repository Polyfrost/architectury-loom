/*
 * This file is part of fabric-loom, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2018-2021 FabricMC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.fabricmc.loom.configuration.providers.minecraft;

import java.io.File;

import org.gradle.api.Project;

import net.fabricmc.loom.LoomGradleExtension;
import net.fabricmc.loom.configuration.providers.MinecraftProviderImpl;
import net.fabricmc.loom.util.Constants;

public class MinecraftLibraryProvider {
	public File MINECRAFT_LIBS;

	private boolean hasLWJGL2 = false;

	public void provide(MinecraftProviderImpl minecraftProvider, Project project) {
		MinecraftVersionMeta versionInfo = minecraftProvider.getVersionInfo();

		initFiles(project, minecraftProvider);


		boolean lwjgl2 = false;
		for (MinecraftVersionMeta.Library library : versionInfo.libraries()) {
			if (library.isValidForOS() && !library.hasNatives() && library.artifact() != null) {
				project.getDependencies().add(Constants.Configurations.MINECRAFT_DEPENDENCIES, library.name());
				lwjgl2 |= library.name().startsWith("org.lwjgl.lwjgl:lwjgl:2.");
			}
		}
		hasLWJGL2 = lwjgl2;
	}

	public boolean hasLWJGL2() {
		return hasLWJGL2;
	}

	private void initFiles(Project project, MinecraftProviderImpl minecraftProvider) {
		LoomGradleExtension extension = LoomGradleExtension.get(project);
		MINECRAFT_LIBS = new File(extension.getFiles().getUserCache(), "libraries");
	}
}
