import cn.hutool.core.lang.Assert;
import com.fa.cim.spc.chartview.service.impl.ChartPointServiceImpl;
import com.fa.cim.spc.commons.struct.outputs.SampleInfoSimpleVO;
import com.fa.cim.spc.commons.struct.outputs.SampleInfoSimpleVO;
import com.fa.cim.spc.job.entity.ChartPointDTO;
import com.fa.cim.spc.job.service.impl.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lxiong
 * @Date: 2022/09/14/18:13
 * @Description:
 * @Copyright 2022, FA Software (chengdu) Co., Ltd. All Rights Reserved.
 */
@Slf4j
public class TestSampleService {


    @Test
    public void testSamplePointPageByDesignSampleId() {
        SampleServiceImpl chartPointService = new SampleServiceImpl();
        ChartPointDTO param = new ChartPointDTO();
        param.setSampleId("6");
        param.setCount(20);
        param.setJobId("1");

        List<SampleInfoSimpleVO> samplePointList = getList(10);
        List<SampleInfoSimpleVO> sampleChartPoints = chartPointService.samplePointPageByDesignSampleId(samplePointList, param);
        Assert.isTrue(sampleChartPoints.size() == 10);
        validateContainsSampleId(sampleChartPoints, param);


        List<SampleInfoSimpleVO> samplePointList1 = getList(20);
        List<SampleInfoSimpleVO> sampleChartPoints1 = chartPointService.samplePointPageByDesignSampleId(samplePointList1, param);
        Assert.isTrue(sampleChartPoints1.size() == 20);
        validateContainsSampleId(sampleChartPoints1, param);


        List<SampleInfoSimpleVO> samplePointList2 = getList(21);
        List<SampleInfoSimpleVO> sampleChartPoints2 = chartPointService.samplePointPageByDesignSampleId(samplePointList2, param);
        Assert.isTrue(sampleChartPoints2.size() == 20);
        validateContainsSampleId(sampleChartPoints2, param);

        param.setSampleId("1");
        List<SampleInfoSimpleVO> samplePointList3 = getList(50);
        List<SampleInfoSimpleVO> sampleChartPoints3 = chartPointService.samplePointPageByDesignSampleId(samplePointList3, param);
        Assert.isTrue(sampleChartPoints3.size() == 20);
        validateContainsSampleId(sampleChartPoints3, param);


        param.setSampleId("1");
        List<SampleInfoSimpleVO> samplePointList4 = getList(200);
        List<SampleInfoSimpleVO> sampleChartPoints4 = chartPointService.samplePointPageByDesignSampleId(samplePointList4, param);
        Assert.isTrue(sampleChartPoints4.size() == 20);
        validateContainsSampleId(sampleChartPoints4, param);


    }

    public void validateContainsSampleId(List<SampleInfoSimpleVO> sampleChartPoints, ChartPointDTO param) {
        int idx = -1;
        for (int i = 0; i < sampleChartPoints.size(); i++) {
            SampleInfoSimpleVO sampleChartPointVO = sampleChartPoints.get(i);
            if (sampleChartPointVO.getSampleId().toString().equals(param.getSampleId())) {
                idx = i;
            }
        }
        Assert.isTrue(idx > -1);
    }

    public List<SampleInfoSimpleVO> getList(int size) {
        LocalDateTime now = LocalDateTime.now();
        List<SampleInfoSimpleVO> samplePointList = new ArrayList<>();
        for (long i = 0; i < size; i++) {
            SampleInfoSimpleVO s = new SampleInfoSimpleVO();
            s.setSampleId(i + 1);
            samplePointList.add(s);
        }
        return samplePointList;
    }


}
