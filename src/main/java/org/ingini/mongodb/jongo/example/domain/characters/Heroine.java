package org.ingini.mongodb.jongo.example.domain.characters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import org.ingini.mongodb.jongo.example.domain.beasts.Beast;
import org.ingini.mongodb.jongo.example.domain.weapons.Weapon;

import javax.annotation.concurrent.Immutable;
import java.util.Set;


/**
 * Copyright (c) 2013 Ivan Hristov
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
public class Heroine extends HumanCharacter {

    @JsonCreator
    public Heroine(@JsonProperty(FIRST_NAME) String firstName,
                   @JsonProperty(LAST_NAME) String lastName,
                   @JsonProperty(ADDRESS) Address address,
                   @JsonProperty(CHILDREN) Set<? extends HumanCharacter> children,
                   @JsonProperty(BEASTS) Set<? extends Beast> beasts,
                   @JsonProperty(WEAPON) Weapon weapon) {
        super(firstName, lastName, Gender.FEMALE, address, children, beasts, weapon);
    }

    public static Heroine createHeroineWithoutChildrenAndNoBeasts(String firstName, String lastName,
                                                                  Address address, Weapon weapon) {
        return new Heroine(firstName, lastName, address, null, null, weapon);
    }

    public static Heroine addBeast(Heroine heroine, Beast beast) {
        return new Heroine(heroine.getFirstName(), heroine.getLastName(), heroine.getAddress(),
                heroine.getChildren(), Sets.newHashSet(beast), heroine.getWeapon());
    }

    public static HumanCharacter createHeroineWithoutChildrenNoBeastsAndNoWeapon(String firstName, String lastName,
                                                                                 Address address) {
        return createHeroineWithoutChildrenAndNoBeasts(firstName, lastName, address, null);
    }
}
