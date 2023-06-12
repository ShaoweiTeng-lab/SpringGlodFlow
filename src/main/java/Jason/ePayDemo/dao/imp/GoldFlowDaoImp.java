package Jason.ePayDemo.dao.imp;

import Jason.ePayDemo.dao.GoldFlowDao;
import Jason.ePayDemo.dao.rowMapper.GoldFlowRowMapper;
import Jason.ePayDemo.model.GoldFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class GoldFlowDaoImp  implements GoldFlowDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public void createGoldFlow(GoldFlow goldFlow) {
        String sql = "INSERT INTO gold_flow(gold_flow_num,isPay) VALUES(:gold_flow_num,:isPay)";
        Map<String ,Object> map =new HashMap<>();
        map.put("gold_flow_num",goldFlow.getGoldFlowNum());
        map.put("isPay",goldFlow.getIsPay());
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void createUserGoldFlows(int userId, int goldFlow) {
        String sql = "INSERT INTO user_Gold_Flows(user_Id,goldflow_Num) VALUES(:user_Id,:goldflow_Num)";
        Map<String ,Object> map =new HashMap<>();
        map.put("user_Id",userId);
        map.put("goldflow_Num",goldFlow);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public GoldFlow getGoldFlowNumOrderByNum(String goldFlowNum) {
        String sql = "select goldFlowNum,isPay from gold_flow where goldFlowNum = :goldFlowNum";
        Map<String ,Object> map =new HashMap<>();
        map.put("goldFlowNum",goldFlowNum);
        List<GoldFlow> rs=   namedParameterJdbcTemplate.query(sql, map,new GoldFlowRowMapper());
        if(rs.size() > 0)
            return rs.get(0);
        return null;
    }
}
