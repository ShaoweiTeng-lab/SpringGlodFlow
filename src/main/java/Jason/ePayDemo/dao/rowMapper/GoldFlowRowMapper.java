package Jason.ePayDemo.dao.rowMapper;

import Jason.ePayDemo.model.GoldFlow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoldFlowRowMapper implements RowMapper<GoldFlow> {
    @Override
    public GoldFlow mapRow(ResultSet rs, int rowNum) throws SQLException {
        GoldFlow goldFlow = new GoldFlow();
        goldFlow.setGoldFlowNum(rs.getString("gold_flow_num"));
        goldFlow.setIsPay(rs.getString("isPay"));
        return goldFlow;
    }
}
