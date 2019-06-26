package misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MiscTestBed
{
    private static final Logger logger = LoggerFactory.getLogger(MiscTestBed.class);

    private static void genStr()
    {
        String updateSql1 = "update PKT_CONSOL_LOCN set CURR_UOM_QTY = CURR_UOM_QTY - 1 , CURR_WT = CURR_WT - :lpnWeight "
                + ", CURR_VOL = CURR_VOL - :lpnVolume "
                + ", USER_ID = ?, LAST_UPDATED_DTTM = ?, "
                + "LOCN_STAT_CODE = CASE WHEN (DIRCT_UOM_QTY - 1 <=0 AND CURR_UOM_QTY <=0 AND DIRCT_WT - :lpnWeight "
                + "<=0 AND CURR_WT <=0 AND CURR_VOL <=0 AND DIRCT_VOL - :lpnVolume "
                + " <=0) THEN 99"
                + " WHEN ((DIRCT_UOM_QTY + CURR_UOM_QTY - 1 < MAX_UOM_QTY) AND (DIRCT_WT + :lpnWeight "
                + "  + CURR_WT - 1 < MAX_WT) AND (DIRCT_VOL + :lpnVolume "
                + " + CURR_VOL - 1 < MAX_VOL)) THEN 10 " + "ELSE 10 " + "END  where REC_TYPE = ? and LOCN_ID = ? and (WAVE_NBR = ? or WAVE_NBR is null)";

        String updateSql2 = "update PKT_CONSOL_LOCN set DIRCT_UOM_QTY = DIRCT_UOM_QTY - 1 , DIRCT_WT = DIRCT_WT - :lpnWeight "
                + ", DIRCT_VOL = DIRCT_VOL - :lpnVolume "
                + ", USER_ID = ?, LAST_UPDATED_DTTM = ?, "
                + "LOCN_STAT_CODE = CASE WHEN (DIRCT_UOM_QTY - 1 <=0 AND CURR_UOM_QTY <=0 AND DIRCT_WT - :lpnWeight "
                + "<=0 AND CURR_WT <=0 AND CURR_VOL <=0 AND DIRCT_VOL - :lpnVolume "
                + " <=0) THEN 99 "
                + " WHEN ((DIRCT_UOM_QTY - 1 + CURR_UOM_QTY < MAX_UOM_QTY) AND (DIRCT_WT - :lpnWeight "
                + "  + CURR_WT < MAX_WT) AND (DIRCT_VOL - :lpnVolume "
                + " + CURR_VOL < MAX_VOL)) THEN 10 " + "ELSE 10 " + "END  where REC_TYPE = ? and LOCN_ID = ?  and (WAVE_NBR = ? or WAVE_NBR is null)";

        System.out.println("updateSql1=" + updateSql1);
        System.out.println("updateSql2=" + updateSql2);
    }

    private static void getNullString() {
        String a = "null";
        String b = null;

        assert(String.valueOf(a).equals(String.valueOf(b)));
    }
    public static void main(String[] args)
    {
        genStr();
        getNullString();
        List<Integer> ints = new ArrayList<>();
        ints.add(0);

        Map<String, Integer> amap = new HashMap<String, Integer>() {{
            put("1", 1);
            put("2", 2);
        }};
        Set<String> keys = amap.keySet();
        Set<Map.Entry<String, Integer>> entrySet = amap.entrySet();
        Collection<Integer> values = amap.values();
        int min = values.stream().min(Integer::compareTo).get();
        int sum = values.stream().reduce(0, Integer::sum);
        logger.debug("min = {}", min);
        logger.debug("sum = {}", sum);
        logger.debug("sum2 = {}", values.stream().mapToInt(Integer::intValue).sum());
    }
}
