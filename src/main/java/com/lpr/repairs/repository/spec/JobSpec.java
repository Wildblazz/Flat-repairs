package com.lpr.repairs.repository.spec;

import com.lpr.repairs.dto.param.search.JobSearchParam;
import com.lpr.repairs.model.Job;
import com.lpr.repairs.model.JobCategory;
import com.lpr.repairs.model.JobCategory_;
import com.lpr.repairs.model.Job_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

import static com.lpr.repairs.repository.spec.SpecBuilder.SearchOperation.EQUALITY;
import static com.lpr.repairs.repository.spec.SpecBuilder.SearchType.AND;
import static com.lpr.repairs.repository.spec.SpecBuilder.addSpec;
import static com.lpr.repairs.repository.spec.SpecBuilder.equalCriteria;
import static com.lpr.repairs.repository.spec.SpecBuilder.getJoinedTable;
import static com.lpr.repairs.repository.spec.SpecBuilder.getNonNullSpecs;
import static com.lpr.repairs.repository.spec.SpecBuilder.likeCriteria;

@Component
public class JobSpec {
  public Optional<Specification<Job>> buildSearchSpec(JobSearchParam searchParam) {
    var specList = new ArrayList<Specification<Job>>();
    addSpec(equalCriteria(Job_.ID, searchParam.getId()), specList);
    addSpec(likeCriteria(Job_.NAME, searchParam.getName()), specList);
    addSpec(likeCriteria(Job_.DESCRIPTION, searchParam.getDescription()), specList);
    addSpec(getJoinedTable(Job.class, JobCategory.class, Job_.JOB_CATEGORY, JobCategory_.ID, searchParam.getJobCategoryId(), EQUALITY), specList);
    addSpec(getJoinedTable(Job.class, JobCategory.class, Job_.JOB_CATEGORY, JobCategory_.NAME, searchParam.getJobCategoryName(), EQUALITY), specList);

    return specList.isEmpty() ? Optional.empty() : Optional.of(getNonNullSpecs(specList, AND));
  }
}
