/*
 * SonarSource :: .NET :: Shared library
 * Copyright (C) 2014-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.dotnet.shared.plugins;

import org.junit.Test;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.config.PropertyDefinitions;
import org.sonar.api.config.internal.MapSettings;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractSolutionConfigurationTest {

  @Test
  public void analyzeGeneratedCodeIsTrue() {
    AbstractSolutionConfiguration config = new AbstractSolutionConfiguration(
      createSettings("true").asConfig(),
      "cs") {
    };
    assertThat(config.analyzeGeneratedCode()).isTrue();
  }

  @Test
  public void analyzeGeneratedCodeIsFalse() {
    AbstractSolutionConfiguration config = new AbstractSolutionConfiguration(
      createSettings("false").asConfig(),
      "cs") {
    };
    assertThat(config.analyzeGeneratedCode()).isFalse();
  }

  private MapSettings createSettings(String analyzeGeneratedCode) {
    AbstractPropertyDefinitions definitions = new AbstractPropertyDefinitions(
      "cs",
      "C#",
      ".cs",
      SonarRuntimeImpl.forSonarQube(Version.create(7, 4), SonarQubeSide.SERVER)) {
    };
    MapSettings settings = new MapSettings(new PropertyDefinitions(definitions.create()));
    settings.setProperty("sonar.cs.analyzeGeneratedCode", analyzeGeneratedCode);
    return settings;
  }

}
