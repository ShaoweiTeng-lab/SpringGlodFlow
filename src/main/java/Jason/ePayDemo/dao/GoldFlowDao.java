package Jason.ePayDemo.dao;

import Jason.ePayDemo.model.GoldFlow;

public interface GoldFlowDao {
    void createGoldFlow(GoldFlow goldFlow);
    void createUserGoldFlows(int userId,int goldFlow);
    GoldFlow getGoldFlowNumOrderByNum(String goldFlowNum);
}
