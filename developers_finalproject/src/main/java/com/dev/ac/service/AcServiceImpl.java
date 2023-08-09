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
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;

@Service
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
		return dao.acHeart(session,no);
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

	@Transactional
	@Override
	public int insertAc(Accommodation ac) {
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
					System.out.println(ac.getAfa().getAfaId());
					ac.getAfa().getAfal().get(i).setAfaId(ac.getAfa().getAfaId());
					result += dao.insertAfal(session, ac.getAfa().getAfal().get(i));
				}
			}
			if (result == ac.getAcFiles().size()+ac.getAfa().getAfal().size()+ 2) {
				for (int i = 0; i < ac.getArv().size(); i++) {
					ac.getArv().get(i).setAcId(ac.getAcId());
					result += dao.insertArv(session, ac.getArv().get(i));
				}
			}
		}
		return result;
	}

	@Override
	public List<AcFile>  deleteImage(int acId) {
		return dao.deleteImage(session,acId);
	}

	@Override
	public List<AfaList> deleteAfalImage(int acId) {
		AcFacilities afa=dao.deleteAfa(session, acId);
		int afaId=afa.getAfaId();
		return dao.deleteAfalImage(session,afaId);
	}

	@Override
	public int deleteAc(int acId) {
		return dao.deleteAc(session,acId);
	}

	@Override
	public Accommodation updateRegist(int acId) {
		return dao.updateRegist(session,acId);
	}
	



}
