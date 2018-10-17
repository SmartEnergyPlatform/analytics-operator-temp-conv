/*
 * Copyright 2018 InfAI (CC SES)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.infai.seits.sepl.operators.Helper;
import org.infai.seits.sepl.operators.Message;
import org.infai.seits.sepl.operators.OperatorInterface;

public class TempConv implements OperatorInterface {

    public static String currentUnit = Helper.getEnv("currentUnit", "C");
    public static String convertToUnit = Helper.getEnv("convertToUnit", "F");

    @Override
    public void run(Message message) {
        Double oldTemp = message.getInput("temp").getValue();
        Double newTemp;

        switch (currentUnit) {
            case "F":
                switch (convertToUnit) {
                    case "C":
                        System.out.print(oldTemp);
                        newTemp = (oldTemp - 32.0) * (5.0 / 9.0);
                        break;
                    case "K":
                        newTemp = (oldTemp + 459.67) * (5.0 / 9.0);
                        break;
                    default:
                        newTemp = oldTemp;
                        break;
                }
                break;
            case "K":
                switch (convertToUnit) {
                    case "C":
                        newTemp = oldTemp - 273.15;
                        break;
                    case "F":
                        newTemp = oldTemp * (9.0 / 5.0) - 459.67;
                        break;
                    default:
                        newTemp = oldTemp;
                        break;
                }
                break;
            case "C":
                switch (convertToUnit) {
                    case "K":
                        newTemp = oldTemp + 273.15;
                        break;
                    case "F":
                        newTemp = oldTemp * 1.8 + 32;
                        break;
                    default:
                        newTemp = oldTemp;
                        break;
                }
                break;
            default:
                newTemp = oldTemp;
                break;
        }

        message.output("temp", newTemp);
    }

    @Override
    public void config(Message message) {
        message.addInput("temp");
    }
}
