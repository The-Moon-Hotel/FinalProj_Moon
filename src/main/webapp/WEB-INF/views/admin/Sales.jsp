<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/inc/top" />
<link rel="stylesheet" href="<c:url value='/css/admin-sales.css' />">
<c:url var="salesUrl" value="/admin/Sales" />

<main class="sales-page">
	<header class="sales-hero">
		<div class="sales-shell sales-hero-inner">
			<div>
				<p class="sales-hero-label">호텔 운영</p>
				<h1>매출 현황</h1>
				<p class="sales-hero-copy">예약의 체크인 날짜를 기준으로 집계합니다.</p>
			</div>
			<c:if test="${empty dateError}">
				<div class="sales-scope">
					<span><c:out value="${branchLabel}" /></span>
					<p><time datetime="${startDate}">${startDate}</time> <span aria-hidden="true">–</span> <time datetime="${endDate}">${endDate}</time></p>
				</div>
			</c:if>
		</div>
	</header>

	<div class="sales-shell sales-content">
		<section class="sales-panel sales-filter" aria-labelledby="sales-filters-heading">
			<form id="sales-filter" action="${salesUrl}" method="get">
				<div class="sales-filter-heading">
					<h2 id="sales-filters-heading">조회 조건</h2>
					<div class="sales-presets" id="sales-presets" role="group" aria-label="자주 쓰는 기간" hidden>
						<c:forEach var="preset" items="${presets}">
							<button type="button" data-start="${preset.start}" data-end="${preset.end}"
									aria-pressed="${startDate eq preset.start and endDate eq preset.end}">${preset.label}</button>
						</c:forEach>
					</div>
				</div>
				<div class="sales-filter-fields">
					<div class="sales-field">
						<label for="sales-branch">지점</label>
						<select id="sales-branch" name="SearchBranchType">
							<option value="all" ${branch eq 'all' ? 'selected' : ''}>전체 지점</option>
							<c:forEach var="name" items="${branches}">
								<option value="${name}" ${branch eq name ? 'selected' : ''}>${name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="sales-field">
						<label for="sales-category">매출 구분</label>
						<select id="sales-category" name="SearchSalesType">
							<option value="3" ${category eq '3' ? 'selected' : ''}>객실 · 부대시설</option>
							<option value="1" ${category eq '1' ? 'selected' : ''}>객실</option>
							<option value="2" ${category eq '2' ? 'selected' : ''}>부대시설</option>
						</select>
					</div>
					<div class="sales-field">
						<label for="sales-start">시작일</label>
						<input id="sales-start" name="searchDate1" type="date" value="<c:out value='${startDate}' />" required>
					</div>
					<div class="sales-field">
						<label for="sales-end">종료일</label>
						<input id="sales-end" name="searchDate2" type="date" value="<c:out value='${endDate}' />" required>
					</div>
					<button class="sales-submit" type="submit">매출 조회</button>
				</div>
				<c:if test="${not empty dateError}">
					<p class="sales-date-error" role="alert">${dateError}</p>
				</c:if>
			</form>
		</section>

		<c:if test="${empty dateError}">
			<section class="sales-summary" aria-label="매출 요약">
				<c:if test="${showRooms and showFacilities}">
					<div class="sales-panel sales-metric sales-metric-total">
						<h2>총 매출</h2>
						<p class="sales-metric-amount"><fmt:formatNumber value="${totalRevenue}" /><span>원</span></p>
						<p class="sales-metric-detail">객실과 부대시설 합계</p>
					</div>
				</c:if>
				<c:if test="${showRooms}">
					<div class="sales-panel sales-metric">
						<h2><span class="sales-key sales-key-room" aria-hidden="true"></span>객실 매출</h2>
						<p class="sales-metric-amount"><fmt:formatNumber value="${roomRevenue}" /><span>원</span></p>
						<a class="sales-metric-detail" href="#sales-rooms">예약 <fmt:formatNumber value="${reservationCount}" />건 <span aria-hidden="true">↓</span></a>
					</div>
				</c:if>
				<c:if test="${showFacilities}">
					<div class="sales-panel sales-metric">
						<h2><span class="sales-key sales-key-facility" aria-hidden="true"></span>부대시설 매출</h2>
						<p class="sales-metric-amount"><fmt:formatNumber value="${facilityRevenue}" /><span>원</span></p>
						<a class="sales-metric-detail" href="#sales-facilities">이용 인원 <fmt:formatNumber value="${visitorCount}" />명 <span aria-hidden="true">↓</span></a>
					</div>
				</c:if>
			</section>

			<div class="sales-charts ${showRooms and showFacilities ? 'sales-charts-split' : ''}">
				<section class="sales-panel sales-chart" aria-labelledby="branch-chart-heading">
					<div class="sales-chart-heading">
						<h2 id="branch-chart-heading">지점별 매출</h2>
						<div class="sales-legend">
							<c:if test="${showRooms}"><span><i class="sales-key sales-key-room" aria-hidden="true"></i>객실</span></c:if>
							<c:if test="${showFacilities}"><span><i class="sales-key sales-key-facility" aria-hidden="true"></i>부대시설</span></c:if>
						</div>
					</div>
					<ul class="sales-branch-list">
						<c:forEach var="row" items="${branchChart}">
							<li>
								<span class="sales-branch-name"><c:out value="${row.label}" /></span>
								<span class="sales-branch-track" aria-hidden="true">
									<span class="sales-branch-mark" style="--branch-width: ${row.width}%; --branch-room-share: ${row.roomShare}%;"></span>
								</span>
								<strong class="sales-branch-amount"><fmt:formatNumber value="${row.total}" /><small>원</small></strong>
							</li>
						</c:forEach>
					</ul>
				</section>

				<c:if test="${showRooms and showFacilities}">
					<section class="sales-panel sales-chart" aria-labelledby="sales-composition-heading">
						<div class="sales-chart-heading"><h2 id="sales-composition-heading">매출 구성</h2></div>
						<div class="sales-composition">
							<div class="sales-donut ${totalRevenue le 0 ? 'sales-donut-empty' : ''}" style="--room-share: ${roomShare}%;" aria-hidden="true"></div>
							<dl>
								<div><dt><i class="sales-key sales-key-room" aria-hidden="true"></i>객실</dt><dd><c:choose><c:when test="${totalRevenue gt 0}">${roomShare}%</c:when><c:otherwise>—</c:otherwise></c:choose></dd></div>
								<div><dt><i class="sales-key sales-key-facility" aria-hidden="true"></i>부대시설</dt><dd><c:choose><c:when test="${totalRevenue gt 0}">${facilityShare}%</c:when><c:otherwise>—</c:otherwise></c:choose></dd></div>
							</dl>
						</div>
					</section>
				</c:if>
			</div>

			<c:if test="${showRooms}">
				<section class="sales-panel sales-breakdown" id="sales-rooms" aria-labelledby="sales-rooms-heading">
					<header class="sales-breakdown-heading">
						<h2 id="sales-rooms-heading"><i class="sales-key sales-key-room" aria-hidden="true"></i>객실 매출 내역</h2>
						<p><fmt:formatNumber value="${reservationCount}" />건 <span aria-hidden="true">·</span> <strong><fmt:formatNumber value="${roomRevenue}" />원</strong></p>
					</header>
					<c:choose>
						<c:when test="${empty rooms}"><p class="sales-empty">선택한 기간과 지점에 객실 매출 내역이 없습니다.</p></c:when>
						<c:otherwise>
							<div class="sales-table-scroll" tabindex="0" role="region" aria-labelledby="sales-rooms-heading">
								<table class="sales-table">
									<thead><tr><th scope="col">체크인</th><th scope="col">지점</th><th scope="col">객실 타입</th><th scope="col" class="sales-number">기준가 / 박</th><th scope="col" class="sales-number">예약 건수</th><th scope="col" class="sales-number">매출</th></tr></thead>
									<tbody>
										<c:forEach var="row" items="${rooms}">
											<tr>
												<td><c:out value="${row.checkInDate}" /></td><td><c:out value="${row.branchName}" /></td><td><c:out value="${row.roomType}" /></td>
												<td class="sales-number"><fmt:formatNumber value="${row.roomRate}" />원</td><td class="sales-number"><fmt:formatNumber value="${row.reservationCount}" />건</td><td class="sales-number sales-amount"><fmt:formatNumber value="${row.roomRevenue}" />원</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</section>
			</c:if>

			<c:if test="${showFacilities}">
				<section class="sales-panel sales-breakdown" id="sales-facilities" aria-labelledby="sales-facilities-heading">
					<header class="sales-breakdown-heading">
						<h2 id="sales-facilities-heading"><i class="sales-key sales-key-facility" aria-hidden="true"></i>부대시설 매출 내역</h2>
						<p><fmt:formatNumber value="${visitorCount}" />명 <span aria-hidden="true">·</span> <strong><fmt:formatNumber value="${facilityRevenue}" />원</strong></p>
					</header>
					<c:choose>
						<c:when test="${empty facilities}"><p class="sales-empty">선택한 기간과 지점에 부대시설 매출 내역이 없습니다.</p></c:when>
						<c:otherwise>
							<div class="sales-table-scroll" tabindex="0" role="region" aria-labelledby="sales-facilities-heading">
								<table class="sales-table">
									<thead><tr><th scope="col">체크인</th><th scope="col">지점</th><th scope="col">시설</th><th scope="col" class="sales-number">성인</th><th scope="col" class="sales-number">아동</th><th scope="col" class="sales-number">성인 요금 합계</th><th scope="col" class="sales-number">아동 요금 합계</th><th scope="col" class="sales-number">매출</th></tr></thead>
									<tbody>
										<c:forEach var="row" items="${facilities}">
											<tr>
												<td><c:out value="${row.checkInDate}" /></td><td><c:out value="${row.branchName}" /></td><td><c:out value="${row.facilityName}" /></td>
												<td class="sales-number"><fmt:formatNumber value="${row.adults}" />명</td><td class="sales-number"><fmt:formatNumber value="${row.children}" />명</td>
												<td class="sales-number"><fmt:formatNumber value="${row.adultRevenue}" />원</td><td class="sales-number"><fmt:formatNumber value="${row.childRevenue}" />원</td><td class="sales-number sales-amount"><fmt:formatNumber value="${row.facilityRevenue}" />원</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:otherwise>
					</c:choose>
				</section>
			</c:if>
		</c:if>
	</div>
</main>
<script>
	document.title = '매출 현황 | The Moon Hotel';
	const salesPresets = document.getElementById('sales-presets');
	const salesForm = document.getElementById('sales-filter');
	const salesStart = document.getElementById('sales-start');
	const salesEnd = document.getElementById('sales-end');
	for (const field of [salesStart, salesEnd]) {
		field.addEventListener('input', function () {
			for (const button of salesPresets.querySelectorAll('button')) {
				button.setAttribute('aria-pressed', button.dataset.start === salesStart.value && button.dataset.end === salesEnd.value);
			}
		});
	}
	salesPresets.hidden = false;
	salesPresets.addEventListener('click', function (event) {
		const preset = event.target.closest('button[data-start]');
		if (preset) {
			salesStart.value = preset.dataset.start;
			salesEnd.value = preset.dataset.end;
			salesForm.requestSubmit();
		}
	});
</script>
<jsp:include page="../inc/footer.jsp" />
