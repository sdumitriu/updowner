/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/
 */
package org.phenotips.utilities.updowner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * Simple utility that translates a localization bundle into upside-down.
 *
 * @version $Id$
 */
public class UpDowner
{
    private UpDowner()
    {
        // Hidden constructor to prevent initialization
    }

    /**
     * Main method, loads each {@code .properties} file receives as an argument, turns upside down each translation
     * value, then prints the result back into a file with the {@code en_UD} locale key appended.
     *
     * @param args the list of files to process
     */
    public static void main(String[] args)
    {
        for (String arg : args) {
            try (InputStream in = new FileInputStream(arg);
                OutputStream out = new FileOutputStream(arg.replaceAll(".properties$", "_en_UD.properties"))) {
                Properties prop = new Properties();
                prop.load(in);
                for (Entry<Object, Object> entry : prop.entrySet()) {
                    prop.setProperty(entry.getKey().toString(), turn(entry.getValue().toString()));
                }
                prop.store(out, null);
            } catch (Exception ex) {
                System.err.println("Failed to process " + arg + ": " + ex.getMessage());
            }
        }
    }

    private static String turn(String value)
    {
        return StringUtils.reverse(StringUtils.replaceEach(value,
            "ABCDEFGJKLMPQRTUVWYabcdefghijklmnopqrstuvwxyz123456789'.,!?<>[](){}%".split(""),
            "ⱯᗺƆᗡƎℲ⅁ՐꞰꞀWԀꝹᴚ⟘∩ΛM⅄ɐqɔpǝɟᵷɥᴉɾʞꞁɯuodbɹsʇnʌʍxʎz⥝ᘔƐ߈ϛ9ㄥ86,˙'¡¿><][)(}{%".split("")));
    }
}
