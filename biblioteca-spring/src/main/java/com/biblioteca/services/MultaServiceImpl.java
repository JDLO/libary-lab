package com.biblioteca.services;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import org.hibernate.grammars.hql.HqlParser.DayContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entities.Lector;
import com.biblioteca.entities.Multa;
import com.biblioteca.entities.Prestamo;
import com.biblioteca.repositories.MultaRepository;
import com.fasterxml.jackson.datatype.jsr310.ser.MonthDaySerializer;

@Service
public class MultaServiceImpl implements MultaService {

	@Autowired
	private MultaRepository repositorio;

	@Override
	public Multa listarId(long id) {
		return repositorio.findById(id);
	}

	@Override
	public List<Multa> listar() {
		return repositorio.findAll();
	}

	@Override
	public Multa agregar(Multa u) {
		return repositorio.save(u);
	}

	@Override
	public Multa modificar(Multa m) {
		Multa original = repositorio.findById(m.getId());
		if (m.getfInicio() != null) {
			original.setfInicio(m.getfInicio());
		}
		if (m.getfFin() != null) {
			original.setfFin(m.getfFin());
		}
		return repositorio.save(original);
	}

	@Override
	public void delete(long id) {
		Multa u = repositorio.findById(id);
		if (u != null) {
			repositorio.delete(u);
		}

	}

	@Override
	public boolean actualizarMultasSistema(Prestamo prestamoMoroso, LocalDate fechaActual) {

		Lector lectorMoroso = prestamoMoroso.getLector();
		LocalDate fechaInicio = prestamoMoroso.getInicio();
		LocalDate fechaEntregaEsperada = fechaInicio.plus(30, ChronoUnit.DAYS);

		// se calcula la los dias de mora y la fecha en la cual la multa termina
		int diasMora = Period.between(fechaEntregaEsperada, fechaActual).getDays();
		int diasMulta = diasMora * 2;
		LocalDate fechaMultaFin = fechaActual.plus(diasMulta, ChronoUnit.DAYS);

		Multa multa = new Multa();
		multa.setfInicio(fechaActual);
		multa.setfFin(fechaMultaFin);
		multa.setLector(lectorMoroso);
		try {
			this.agregar(multa);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}

	}

}
