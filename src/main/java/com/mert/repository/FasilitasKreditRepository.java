package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.FasilitasKredit;

public interface FasilitasKreditRepository extends JpaRepository<FasilitasKredit, String> {
	
	@Query(value = "select * from fasilitaskredit n where n.no_nasabah = :nonasabah ", nativeQuery=true)
	List<FasilitasKredit> findByNoNasabah(@Param("nonasabah") Long nonasabah);
	
	@Query(value = "select * from fasilitaskredit n where lower(n.nama_nasabah) like %:namanasabah% ", nativeQuery=true)
	List<FasilitasKredit> findByNamaNasabah(@Param("namanasabah") String namanasabah);
	
	@Query(value = "select " + 
			"no_fasilitas, " + 
			"no_nasabah, " + 
			"nama_nasabah, " + 
			"produk, " + 
			"valuta, " + 
			"cast (plafond as decimal) as plafond, " + 
			"kurs, " + 
			"cast (eqv_plafond as decimal) as eqv_plafond," + 
			"tenor, " + 
			"bunga_persen, " + 
			"hitung_bunga, " + 
			"segment, " + 
			"take_over, " + 
			"bank, " + 
			"fas_khusus, " + 
			"joint_flag, " + 
			"cif_joint, " + 
			"nama_joint, " + 
			"seq_joint, " + 
			"pinalti_bunga_persen, " + 
			"pinalti_pokok_persen, " + 
			"pinalti_lunas_persen, " + 
			"pinalti_flag, " + 
			"agunan, " + 
			"nilai_bank, " + 
			"ltv_persen, " + 
			"pledging_persen, " + 
			"tujuan_kredit, " + 
			"provisi_persen, " + 
			"provisi_amount, " + 
			"amt_admin, " + 
			"amt_notaris, " + 
			"amt_asuransi, " + 
			"amt_appraisal, " + 
			"amt_materai, " + 
			"note_putusan, " + 
			"putusan, " + 
			"reason, " + 
			"no_ref, " + 
			"pembayaran_biaya_date, " + 
			"pembayaran_biaya_amount, " + 
			"pembayaran_biaya_tranref, " + 
			"aktifasi_date, " + 
			"no_rekening, " + 
			"accrual_provisi, " + 
			"accrual_admin " + 
			"from fasilitaskredit " + 
			"where no_fasilitas = :nofasilitas ", nativeQuery=true)
	FasilitasKredit findOneX(@Param("nofasilitas") String nofasilitas);
	
	
	
	@Query(value = "select f.* " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where coalesce(r.disburse, 0.0) <= 0.0 " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1001(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where coalesce(r.disburse, 0.0) <= 0.0 " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1001Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	
	
	@Query(value = "select f.* " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where r.status_rekening = '1' " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1003(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) " + 
			" from fasilitaskredit f " + 
			" join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			" where r.status_rekening = '1' " + 
			" and r.unit_id = :unitId " +
			" and to_char(f.pembayaran_biaya_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1003Count(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	
	
	@Query(value = "select f.* from fasilitaskredit f " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"join skalaangsuran a on r.no_rekening = a.no_rekening " + 
			"where r.unit_id = :unitId and a.bulan_ke > 0 " + 
			"and to_char(a.due_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	List<FasilitasKredit> customEodCalculation1009(@Param("unitId") String unitId, @Param("strDate") String strDate);
	
	@Query(value = "select count(1) from fasilitaskredit f " + 
			"join rekeningkredit r on f.no_fasilitas = r.no_fasilitas " + 
			"join skalaangsuran a on r.no_rekening = a.no_rekening " + 
			"where r.unit_id = :unitId and a.bulan_ke > 0 " + 
			"and to_char(a.due_date, 'yyyy-MM-dd') = :strDate ", nativeQuery=true)
	Integer customEodCalculation1009Count(@Param("unitId") String unitId, @Param("strDate") String strDate);

}
