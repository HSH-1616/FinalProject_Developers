package com.dev.ac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ac.dao.AcDao;
import com.dev.ac.dto.AcFacilities;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcHeart;
import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.AcPayList;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.AcReview;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;
import com.dev.ac.dto.ArFile;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AcServiceImpl implements AcService {

	@Autowired
	private AcDao dao;

	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Accommodation> acListAll() {
		return dao.acListAll(session);
	}

	@Override
	public List<Accommodation> searchListAll(Map param) {
		return dao.acSearchAll(session, param);
	}

	@Override
	public Accommodation acDetail(int no) {
		return dao.acDetail(session, no);
	}

	@Override
	public List<AcHeart> acHeart(int no) {
		return dao.acHeart(session, no);
	}

	@Override
	public Accommodation acPay(int no) {
		return dao.acPay(session, no);
	}

	@Override
	public AcPay checkPayRefund(int no) {
		return dao.checkPayRefund(session, no);
	}

	@Override
	public int insertPay(AcPay ap, String checkIn, String checkOut) {
		int result = dao.insertPay(session, ap);
		Map param = new HashMap();
		if (result > 0) {
			param.put("acId", ap.getAcId());
			param.put("apId", ap.getApId());
			param.put("checkIn", checkIn);
			param.put("checkOut", checkOut);
			System.out.println(param);
			result += dao.insertReservation(session, param);
		}

		return result;
	}

	@Override
	public int deletePay(int apId) {
		return dao.deletePay(session, apId);
	}

	@Override
	public List<Accommodation> acRefundTest() {
		return dao.acRefundTest(session);
	}

	@Override
	public int insertHeart(Map param) {
		return dao.insertHeart(session, param);
	}

	@Override
	public int deleteHeart(Map param) {
		return dao.deleteHeart(session, param);
	}
	
	@Override
	public List<Accommodation> selectAcAll(Map param) {
		return dao.selectAcAll(session,param);
	}
	
	@Override
	public int selectAcAllCount() {
		return dao.selectAcAllCount(session);
	}
	
	@Transactional
	@Override
	public Map insertAc(Accommodation ac) {
		int result = dao.insertAc(session, ac);
		if (result > 0) {
			for (int i = 0; i < ac.getAcFiles().size(); i++) {
				ac.getAcFiles().get(i).setAcId(ac.getAcId());
				result += dao.insertAcFile(session, ac.getAcFiles().get(i));
			}
			if (result == ac.getAcFiles().size() + 1) {
				ac.getAfa().setAcId(ac.getAcId());
				result += dao.insertAfa(session, ac.getAfa());
			}
			if (result == ac.getAcFiles().size() + 2) {
				for (int i = 0; i < ac.getAfa().getAfal().size(); i++) {
					ac.getAfa().getAfal().get(i).setAfaId(ac.getAfa().getAfaId());
					result += dao.insertAfal(session, ac.getAfa().getAfal().get(i));
				}
			}
			if (result == ac.getAcFiles().size() + ac.getAfa().getAfal().size() + 2) {
				for (int i = 0; i < ac.getArv().size(); i++) {
					ac.getArv().get(i).setAcId(ac.getAcId());
					result += dao.insertArv(session, ac.getArv().get(i));
				}
			}
		}
		;
		Map<String, Object> m = new <String, Object>HashMap();
		m.put("result", result);
		m.put("acId", ac.getAcId());
		return m;
	}

	@Override
	public List<AcFile> deleteImage(int acId) {
		return dao.deleteImage(session, acId);
	}

	@Override
	public List<AfaList> deleteAfalImage(int acId) {
		AcFacilities afa = dao.deleteAfa(session, acId);
		int afaId = afa.getAfaId();
		return dao.deleteAfalImage(session, afaId);
	}

	@Override
	public int deleteAc(int acId) {
		return dao.deleteAc(session, acId);
	}

	@Override
	public Accommodation updateRegist(int acId) {
		return dao.updateRegist(session, acId);
	}

	@Override
	public List<AcReservation> updateRegistArv(int acId) {
		return dao.updateRegistArv(session, acId);
	}

	@Override
	public AcFacilities updateRegistAfa(int acId) {
		return dao.updateRegistAfa(session, acId);
	}

	@Override
	public List<AcFile> updateRegistAf(int acId) {
		return dao.updateRegistAf(session, acId);
	}

	@Override
	public List<AfaList> updateRegistAfal(int afaId) {
		return dao.updateRegistAfal(session, afaId);
	}

	@Override
	public List<AcFile> updateRegistCheckAf(int acId) {
		return dao.updateRegistCheckAf(session, acId);
	}

	@Override
	public int updateRegistDelAf(int acId) {
		return dao.updateRegistDelAf(session, acId);
	}

	@Override
	public List<AfaList> updateRegistCheckAfal(int afaId) {
		return dao.updateRegistCheckAfal(session, afaId);
	}

	@Override
	public int updateRegistDelAfal(int afaId) {
		return dao.updateRegistDelAfal(session, afaId);
	}

	@Override
	public int updateAc(Accommodation ac) {
		int result = dao.updateAc(session, ac);
		log.info("acUpdate 성공 result : " + result);
		if (result > 0) {
			result += dao.updateRegistDelAf(session, ac.getAcId());

			log.info("파일delete 성공 result : " + result);

			if (result > 1) {
				for (int i = 0; i < ac.getAcFiles().size(); i++) {
					result += dao.updateInAcFile(session, ac.getAcFiles().get(i));
				}
				log.info("파일insert 성공 result : " + result);
			}

			if (result > ac.getAcFiles().size() + 1) {
				result += dao.updateAfa(session, ac.getAfa());
				log.info("편의시설update 성공 result : " + result);
			}

			if (result > ac.getAcFiles().size() + 2) {
				result += dao.updateRegistDelAfal(session, ac.getAfa().getAfaId());
				log.info("편의시설 리스트 delete 성공 result : " + result);
			}

			if (result > ac.getAcFiles().size() + 2) {
				for (int i = 0; i < ac.getAfa().getAfal().size(); i++) {
					result += dao.updateInAfal(session, ac.getAfa().getAfal().get(i));
				}
				log.info("편의시설 리스트 insert 성공 result : " + result);
			}

			if (result > ac.getAcFiles().size() + ac.getAfa().getAfal().size() + 2) {
				result += dao.updateDelArv(session, ac.getAcId());
				log.info("예약내역 삭제 성공 result : " + result);
			}

			if (result > ac.getAcFiles().size() + ac.getAfa().getAfal().size() + 3) {
				for (int i = 0; i < ac.getArv().size(); i++) {
					ac.getArv().get(i).setAcId(ac.getAcId());
					System.out.println(ac.getArv().get(i));
					result += dao.updateInArv(session, ac.getArv().get(i));
				}
				log.info("예약내역 등록 성공 result : " + result);
			}

		}
		return result;
	}

	@Override
	public List<AcPayList> acMyPage(String memberId) {
		return dao.acMyPage(session,memberId);
	}

	@Override
	public AcPayList acRefundApply(String orderId) {
		return dao.acRefundApply(session,orderId);
	}

	@Override
	public int updateRefund(Map param) {
		int result=dao.updateRefund(session,param);
		if(result>0) {
			result+=dao.insertRefund(session,param);
		}
		return result;
	}

	@Override
	public int insertReview(AcReview ar) {
		int result=dao.insertReview(session,ar);
		
		if(result>0) {
			for(ArFile arf:ar.getArFiles()) {			
			arf.setArId(ar.getArId());
			log.info("파일 : "+arf);
			result+=dao.insertArf(session,arf);			
			}
		}
		return result;
	}

	@Override
	public List<AcReview> acReview(int no) {
		List<AcReview> ar=dao.acReview(session,no);
		
		for(int i=0;i<ar.size();i++) {
			List<ArFile> arf=dao.acArf(session,ar.get(i).getArId());
			ar.get(i).setArFiles(arf);
		}
		
		return ar;
		
	}

	@Override
	public List<AcReview> checkReview(String memberId) {
		return dao.checkReview(session, memberId);
	}

	@Override
	public List<AcPayList> paymentList(Map param) {
		return dao.paymentList(session,param);
	}

	@Override
	public int paymentListCount() {
		return dao.paymentListCount(session);
	}
	
	@Override
	public List<AcPayList> refundList(Map param) {
		return dao.refundList(session,param);
	}
	
	@Override
	public List<AcPayList> refundOkList(Map param) {
		return dao.refundOkList(session,param);
	}

	@Override
	public int refundListCount() {
		return dao.refundListCount(session);
	}
	
	@Override
	public int refundListCount2() {
		return dao.refundListCount2(session);
	}
	
	@Override
	public int rejectRefund(Map param) {
		int result=dao.rejectRefund(session,param);
		if(result>0) {
			dao.rejectComment(session,param);
		}
		
		return result;
	}

	@Override
	public int updateRefundAdmin(Map param) {
		int result=dao.updateRefundAdmin(session,param);
		if(result>0) {
			result+=dao.insertRefundAdmin(session,param);
		}
		return result;
	}

	@Override
	public List<Accommodation> mypageAcHeart(String memberId) {
		return dao.mypageAcHeart(session,memberId);
	}


}
