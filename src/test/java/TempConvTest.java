/*
 * Copyright 2018 The SEPL Team
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

import com.jayway.jsonpath.JsonPath;
import org.infai.seits.sepl.operators.Builder;
import org.infai.seits.sepl.operators.Message;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class TempConvTest {

    static TempConv tempConv;
    protected JSONObject ob1 = new JSONObject();
    protected JSONObject ob2 = new JSONObject();

    public TempConvTest (){
        ob1.put("device_id", "1").put("value", new JSONObject().put("reading", new JSONObject().put("value", new Double(5))));
        ob2.put("device_id", "1").put("value", new JSONObject().put("reading", new JSONObject().put("value", new Double(10))));
    }

    @Test
    public void testRun(){
        tempConv  = new TempConv();
        Builder builder = new Builder("1", "1");
        JSONArray config = new JSONArray().put(new JSONObject().put("Name","test")
                .put("FilterType", "DeviceId")
                .put("FilterValue", "1")
                .put("Mappings", new JSONArray().put(
                        new JSONObject().put("Source", "value.reading.value").put("Dest","temp"))
                ));
        Message message = new Message(builder.formatMessage(ob1.toString()));
        message.setConfig(config.toString());
        tempConv.config(message);
        tempConv.run(message);
        Assert.assertEquals(new Double(41.0), JsonPath.parse(message.getMessageString()).read("$.analytics.temp"));
    }
}
