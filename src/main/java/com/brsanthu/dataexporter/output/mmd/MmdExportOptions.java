package com.brsanthu.dataexporter.output.mmd;

import com.brsanthu.dataexporter.util.Util;
import com.brsanthu.dataexporter.output.text.TextExportOptions;
import com.brsanthu.dataexporter.model.AlignType;

public class MmdExportOptions extends TextExportOptions {

    private AlignType headerAlignment = AlignType.MIDDLE_CENTER;

	public AlignType getHeaderAlignment() {
        return headerAlignment;
    }
    
    /**
     * Sets the text alignment for header cells. Default alignment is {@link AlignType#MIDDLE_CENTER}
     * 
     * @param headerAlignment the header alignment to use. Cannot be <code>null</code>.
     */
    public MmdExportOptions setHeaderAlignment(AlignType headerAlignment) {
        Util.checkForNotNull(headerAlignment, "headerAlignment");
        
        this.headerAlignment = headerAlignment;
        return this;
    }
  
    public MmdExportOptions() {
        setDelimiter(" | ");
    }
}
