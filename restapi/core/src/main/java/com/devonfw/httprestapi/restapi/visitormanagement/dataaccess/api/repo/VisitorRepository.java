package com.devonfw.httprestapi.restapi.visitormanagement.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.util.Iterator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.devonfw.httprestapi.restapi.visitormanagement.dataaccess.api.VisitorEntity;
import com.devonfw.httprestapi.restapi.visitormanagement.logic.api.to.VisitorSearchCriteriaTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * {@link DefaultRepository} for {@link VisitorEntity}
 */
public interface VisitorRepository extends DefaultRepository<VisitorEntity> {

  /**
   * @param criteria the {@link VisitorSearchCriteriaTo} with the criteria to search.
   * @return the {@link Page} of the {@link VisitorEntity} objects that matched the search. If no pageable is set, it
   *         will return a unique page with all the objects that matched the search.
   */
  default Page<VisitorEntity> findByCriteria(VisitorSearchCriteriaTo criteria) {

    VisitorEntity alias = newDslAlias();
    JPAQuery<VisitorEntity> query = newDslQuery(alias);

    String username = criteria.getUsername();
    if (username != null && !username.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getUsername()), username, criteria.getUsernameOption());
    }
    String password = criteria.getPassword();
    if (password != null && !password.isEmpty()) {
      QueryUtil.get().whereString(query, $(alias.getPassword()), password, criteria.getPasswordOption());
    }
    if (criteria.getPageable() == null) {
      criteria.setPageable(PageRequest.of(0, Integer.MAX_VALUE));
    } else {
      addOrderBy(query, alias, criteria.getPageable().getSort());
    }

    return QueryUtil.get().findPaginated(criteria.getPageable(), query, true);
  }

  /**
   * Add sorting to the given query on the given alias
   * 
   * @param query to add sorting to
   * @param alias to retrieve columns from for sorting
   * @param sort specification of sorting
   */
  public default void addOrderBy(JPAQuery<VisitorEntity> query, VisitorEntity alias, Sort sort) {

    if (sort != null && sort.isSorted()) {
      Iterator<Order> it = sort.iterator();
      while (it.hasNext()) {
        Order next = it.next();
        switch (next.getProperty()) {
          case "username":
            if (next.isAscending()) {
              query.orderBy($(alias.getUsername()).asc());
            } else {
              query.orderBy($(alias.getUsername()).desc());
            }
            break;
          case "password":
            if (next.isAscending()) {
              query.orderBy($(alias.getPassword()).asc());
            } else {
              query.orderBy($(alias.getPassword()).desc());
            }
            break;
          default:
            throw new IllegalArgumentException("Sorted by the unknown property '" + next.getProperty() + "'");
        }
      }
    }
  }

}