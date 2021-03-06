package com.brsanthu.dataexporter.output.texttable;

import java.io.OutputStream;
import java.io.Writer;

import com.brsanthu.dataexporter.DataExporter;

/**
 * Exporter which exports the dataset in Text table format. Text table exporter supports
 * many options and export styles. Check {@link TextTableExportOptions} for more information.
 * <p>
 * 
 * Sample text table is shown below.
 * <pre>
 *  +=====+=======================+==========+===============+==========+==========+==========+==========+
 *  |Line |    Date Purchased     | Item No  |   Item Name   | Shipped? | Quantity |Unit Price|  Price   |
 *  | No  |                       |          |               |          |          |          |          |
 *  +=====+=======================+==========+===============+==========+==========+==========+==========+
 *  |    1|2011/04/07 08:25:33 AM |         1|Laptop         |No        |         1|   $799.78|   $799.78|
 *  |    2|2011/04/04 04:38:08 PM |         2|Mouse          |Yes       |         2|    $49.30|    $98.60|
 *  |    3|2011/04/04 04:04:06 PM |         3|Keyboard       |No        |         5|    $75.00|   $375.00|
 *  +=====+=======================+==========+===============+==========+==========+==========+==========+
 *  </pre>
 *  
 * @author Santhosh Kumar
 *
 */
public class TextTableExporter extends DataExporter {

    public TextTableExporter(OutputStream out) {
        this(new TextTableExportOptions(), out);
    }

    public TextTableExporter(TextTableExportOptions options, OutputStream out) {
        super(new TextTableWriter(options, out));
    }

    public TextTableExporter(Writer out) {
        this(new TextTableExportOptions(), out);
    }
    public TextTableExporter(TextTableExportOptions options, Writer out) {
        super(new TextTableWriter(options, out));
    }
    
    public TextTableExporter() {
        this(System.out);
    }
    public TextTableExporter(TextTableExportOptions options) {
        this(options, System.out);
    }
    
    public TextTableExportOptions getTextTableExportOptions() {
        return (TextTableExportOptions) getOptions();
    }
}
