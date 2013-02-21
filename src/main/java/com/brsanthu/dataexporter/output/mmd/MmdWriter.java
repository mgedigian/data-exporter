package com.brsanthu.dataexporter.output.mmd;

import java.util.ArrayList;
import com.brsanthu.dataexporter.model.AlignType;
import com.brsanthu.dataexporter.model.Column;
import com.brsanthu.dataexporter.util.Util;
import com.brsanthu.dataexporter.output.texttable.TextTableExportOptions;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import com.brsanthu.dataexporter.AbstractDataWriter;
import com.brsanthu.dataexporter.model.CellDetails;
import com.brsanthu.dataexporter.model.HeaderCellDetails;
import com.brsanthu.dataexporter.model.RowDetails;
import com.brsanthu.dataexporter.model.Table;

/**
 * Mmd write which writes the output in Multimarkdown format. Sample output is as follows.
 * 
 * <pre>
 * ||Line No||Date Purchased||Item No||Item Name||Shipped?||Quantity||Unit Price||Price||
 * ||1||2011/04/07 08:48:39 AM||1||Laptop||No||1||$799.78||$799.78||
 * ||2||2011/04/04 05:01:15 PM||2||Mouse||Yes||2||$49.30||$98.60||
 * ||3||2011/04/04 04:27:13 PM||3||Keyboard||No||5||$75.00||$375.00||
 * </pre>
 * 
 * @author Santhosh Kumar
 */
public class MmdWriter extends AbstractDataWriter {
    private List<Column> columns;

    public MmdWriter() {
        this(System.out);
    }

    public MmdWriter(MmdExportOptions options) {
        super(options, System.out);
    }

    public MmdWriter(MmdExportOptions options, OutputStream out) {
        super(options, out);
    }
    
    public MmdWriter(OutputStream out) {
        super(new MmdExportOptions(), out);
    }

    public MmdWriter(MmdExportOptions options, Writer out) {
        super(options, out);
    }

    public MmdWriter(Writer out) {
        super(new MmdExportOptions(), out);
    }
    
    public MmdExportOptions getMmdExportOptions() {
        return (MmdExportOptions) getOptions();
    }
    
    @Override
    public void writeHeader(Table table) {
        columns = table.getColumns();        
        printHeaderCells(formatHeaderCells());
    }    
    
    public void printHeaderCells(List<List<String>> headerLines) {
        for (List<String> row : headerLines) {
            StringBuilder sb = new StringBuilder();
            for (String cell : row) {
                sb.append(cell);
            }
            println(sb.toString());
        }
    }

    // from TextTableWriter
    public List<List<String>> formatHeaderCells() {
        
        int maxHeaderHeight = 0;
        for (Column column : columns) {
            maxHeaderHeight = Math.max(maxHeaderHeight, Column.getMaxRowHeight(column.getWidth(), column.getTitle()));
        }
        int maxHeaderHeightPlusSpecial = maxHeaderHeight + 1;

        //Init the header lines array which would store the formatted/aligned strings
        List<List<String>> headerLines = new ArrayList<List<String>>();
        for (int j = 0; j < maxHeaderHeightPlusSpecial; j++) {
            headerLines.add(new ArrayList<String>());
        }
        
        for (int i = 0; i < columns.size(); i++) {
            
            Column column = columns.get(i);
            AlignType align = getMmdExportOptions().getHeaderAlignment();
            if (align == null) {
                align = column.getAlign();
            }

            List<String> cells = Column.align(column.getWidth(), maxHeaderHeight, align, column.getTitle());
            for (int j = 0; j < maxHeaderHeight; j++) {
                headerLines.get(j).add(cells.get(j));
            }
            // format control for ith column
            String alignment = column.getAlign().getHorizontalAlignment();
            String alignHeader = null; 
            if (alignment == "left") {
                alignHeader = ":" + Util.createString("-", column.getWidth() - 1);   
            } else if (alignment == "center") {
                alignHeader = ":" + Util.createString("-", column.getWidth() - 2) + ":";
            } else if (alignment == "right") {
                alignHeader = Util.createString("-", column.getWidth() - 1) + ":";
            } else {
                alignHeader = Util.createString("-", column.getWidth());
            }
            headerLines.get(maxHeaderHeight).add(alignHeader);
        }
        
        return headerLines;
    }

    @Override
    public void beforeRow(RowDetails rowDetails) {
        println();
        print(getMmdExportOptions().getDelimiter());
    }
    
    @Override
    public void writeRowCell(CellDetails cellDetails) {
        Object cellValue = cellDetails.getCellValue();
        writeCell(cellDetails.getColumnIndex(), cellValue==null?"":cellDetails.getColumn().format(cellDetails));
    }
    
    public void writeCell(int i, String cellValue) {
        String delimiter = getMmdExportOptions().getDelimiter();
        if (i != 0) {
            print(delimiter);
        }
        
        print(cellValue);
    }
    
    @Override
    public void afterRow(RowDetails rowDetails) {
        print(getMmdExportOptions().getDelimiter());
    }
}
