package com.mert.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mert.model.LapKeuDaily;

public interface LapKeuDailyRepository extends JpaRepository<LapKeuDaily, Integer> {
	
	@Query(value = "select * from lapkeudaily " + 
			"where to_char(tanggal, 'yyyy-MM-dd') = :strDate " + 
			"order by id ", nativeQuery=true)
	List<LapKeuDaily> findByTanggal(@Param("strDate") String strDate);
	
	@Query(value = "select " + 
			"ROW_NUMBER () OVER (ORDER BY a.jenis_id, " + 
			"b.posisi_id, b2.unit_id, " + 
			"c.pos_id, c2.unit_id, " + 
			"d.subpos_id, d2.unit_id, " + 
			"e.subsubpos_id, e2.unit_id) as id, " + 
			"f.eodtanggal_date as tanggal, " + 
			"coalesce(b2.unit_id, coalesce(c2.unit_id, coalesce(d2.unit_id, e2.unit_id))) as unit_id, " + 
			"a.jenis_id, " + 
			"b.posisi_id, " + 
			"c.pos_id, " + 
			"d.subpos_id, " + 
			"e.subsubpos_id, " + 
			"coalesce(b2.bukubesar_id, coalesce(c2.bukubesar_id, coalesce(d2.bukubesar_id, e2.bukubesar_id))) as bukubesar_id, " + 
			"coalesce(b3.saldo, coalesce(c3.saldo, coalesce(d3.saldo, e3.saldo))) as value " + 
			"from jenislapkeu a " + 
			"left join posisilapkeu b on a.jenis_id = b.jenis_id " + 
			"left join posisilapkeuunit b2 on b.posisi_id = b2.posisi_id " + 
			"left join rekeningbukubesar b3 on b2.bukubesar_id = b3.no_rekening " + 
			"left join poslapkeu c on b.posisi_id = c.posisi_id " + 
			"left join poslapkeuunit c2 on c.pos_id = c2.pos_id " + 
			"left join rekeningbukubesar c3 on c2.bukubesar_id = c3.no_rekening " + 
			"left join subposlapkeu d on c.pos_id = d.pos_id " + 
			"left join subposlapkeuunit d2 on d.subpos_id = d2.subpos_id " + 
			"left join rekeningbukubesar d3 on d2.bukubesar_id = d3.no_rekening " + 
			"left join subsubposlapkeu e on d.subpos_id = e.subpos_id " + 
			"left join subsubposlapkeuunit e2 on e.subsubpos_id = e2.subsubpos_id " + 
			"left join rekeningbukubesar e3 on e2.bukubesar_id = e3.no_rekening " + 
			"left join eodtanggal f on 1=1 " + 
			"order by a.jenis_id, " + 
			"b.posisi_id, b2.unit_id, " + 
			"c.pos_id, c2.unit_id, " + 
			"d.subpos_id, d2.unit_id, " + 
			"e.subsubpos_id, e2.unit_id ", nativeQuery=true)
	List<LapKeuDaily> customEodLapKeu();
	
	@Query(value = "select count(1) " + 
			"from jenislapkeu a " + 
			"left join posisilapkeu b on a.jenis_id = b.jenis_id " + 
			"left join posisilapkeuunit b2 on b.posisi_id = b2.posisi_id " + 
			"left join rekeningbukubesar b3 on b2.bukubesar_id = b3.no_rekening " + 
			"left join poslapkeu c on b.posisi_id = c.posisi_id " + 
			"left join poslapkeuunit c2 on c.pos_id = c2.pos_id " + 
			"left join rekeningbukubesar c3 on c2.bukubesar_id = c3.no_rekening " + 
			"left join subposlapkeu d on c.pos_id = d.pos_id " + 
			"left join subposlapkeuunit d2 on d.subpos_id = d2.subpos_id " + 
			"left join rekeningbukubesar d3 on d2.bukubesar_id = d3.no_rekening " + 
			"left join subsubposlapkeu e on d.subpos_id = e.subpos_id " + 
			"left join subsubposlapkeuunit e2 on e.subsubpos_id = e2.subsubpos_id " + 
			"left join rekeningbukubesar e3 on e2.bukubesar_id = e3.no_rekening " + 
			"left join eodtanggal f on 1=1 ", nativeQuery=true)
	Integer customEodLapKeuCount();

}
