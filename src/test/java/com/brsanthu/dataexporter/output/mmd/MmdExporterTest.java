package com.brsanthu.dataexporter.output.mmd;

import org.junit.Test;

import com.brsanthu.dataexporter.DataExporterTestBase;

public class MmdExporterTest extends DataExporterTestBase {
    
    public MmdExporterTest() {
        exporter = new MmdExporter(sw);
    }
    
    @Test
    public void testBasic() throws Exception {
        addData();
        exporter.finishExporting();
        
        System.out.println(sw);
        compareText("testBasic.txt", sw.toString());
    }
}
