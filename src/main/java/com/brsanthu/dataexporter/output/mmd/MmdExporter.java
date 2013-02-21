package com.brsanthu.dataexporter.output.mmd;

import java.io.OutputStream;
import java.io.Writer;

import com.brsanthu.dataexporter.DataExporter;
import com.brsanthu.dataexporter.model.Column;

public class MmdExporter extends DataExporter {

    public MmdExporter(OutputStream out) {
        this(new MmdExportOptions(), out);
    }

    public MmdExporter(MmdExportOptions options, OutputStream out) {
        super(new MmdWriter(options, out));
    }

    public MmdExporter(Writer out) {
        this(new MmdExportOptions(), out);
    }
    
    public MmdExporter(MmdExportOptions textExportOptions, Writer out) {
        super(new MmdWriter(textExportOptions, out));
    }
    
    public MmdExporter() {
        this(System.out);
    }
    
    public MmdExporter(MmdExportOptions options) {
        this(options, System.out);
    }

    public MmdExportOptions getTextExportOptions() {
        return (MmdExportOptions) getDataWriter().getOptions();
    }
}
