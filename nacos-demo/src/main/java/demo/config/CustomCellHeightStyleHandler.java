package demo.config;

import com.alibaba.excel.write.style.row.AbstractRowHeightStyleStrategy;
import org.apache.poi.ss.usermodel.Row;

public class CustomCellHeightStyleHandler extends AbstractRowHeightStyleStrategy {

    private final Short headRowHeight;
    private final Short contentRowHeight;

    public CustomCellHeightStyleHandler(Short headRowHeight, Short contentRowHeight) {
        this.headRowHeight = headRowHeight;
        this.contentRowHeight = contentRowHeight;
    }

    @Override
    protected void setHeadColumnHeight(Row row, int relativeRowIndex) {
        row.setHeightInPoints(headRowHeight);
    }

    @Override
    protected void setContentColumnHeight(Row row, int relativeRowIndex) {
        row.setHeightInPoints(contentRowHeight);
    }

}
