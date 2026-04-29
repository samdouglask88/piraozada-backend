package piraozada.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import piraozada.domain.entity.Custo;
import piraozada.domain.entity.Pedido;
import piraozada.repository.CustoRepository;
import piraozada.repository.PedidoRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final PedidoRepository pedidoRepository;
    private final CustoRepository custoRepository;

    public byte[] gerarRelatorioExcel() throws IOException {

        Workbook workbook = new XSSFWorkbook();


        Sheet sheetPedidos = workbook.createSheet("Pedidos");

        // cabeçalho
        Row headerPedidos = sheetPedidos.createRow(0);
        headerPedidos.createCell(0).setCellValue("Data");
        headerPedidos.createCell(1).setCellValue("Cliente");
        headerPedidos.createCell(2).setCellValue("Sabor");
        headerPedidos.createCell(3).setCellValue("Tamanho");
        headerPedidos.createCell(4).setCellValue("Quantidade");
        headerPedidos.createCell(5).setCellValue("Valor Total");


        List<Pedido> pedidos = pedidoRepository.findAll();
        int rowPedido = 1;
        for (Pedido pedido : pedidos) {
            Row row = sheetPedidos.createRow(rowPedido++);
            row.createCell(0).setCellValue(pedido.getDataPedido().toString());
            row.createCell(1).setCellValue(pedido.getNomeCliente());
            row.createCell(2).setCellValue(pedido.getSabor().name());
            row.createCell(3).setCellValue(pedido.getTamanho().name());
            row.createCell(4).setCellValue(pedido.getQuantidade());
            row.createCell(5).setCellValue(pedido.getValorTotal().doubleValue());
        }


        for (int i = 0; i <= 5; i++) {
            sheetPedidos.autoSizeColumn(i);
        }


        Sheet sheetCustos = workbook.createSheet("Custos");


        Row headerCustos = sheetCustos.createRow(0);
        headerCustos.createCell(0).setCellValue("Data");
        headerCustos.createCell(1).setCellValue("Produto");
        headerCustos.createCell(2).setCellValue("Fornecedor");
        headerCustos.createCell(3).setCellValue("Quantidade");
        headerCustos.createCell(4).setCellValue("Valor Unitário");
        headerCustos.createCell(5).setCellValue("Valor Total");


        List<Custo> custos = custoRepository.findAll();
        int rowCusto = 1;
        for (Custo custo : custos) {
            Row row = sheetCustos.createRow(rowCusto++);
            row.createCell(0).setCellValue(custo.getData().toString());
            row.createCell(1).setCellValue(custo.getProduto());
            row.createCell(2).setCellValue(custo.getFornecedor());
            row.createCell(3).setCellValue(custo.getQuantidade());
            row.createCell(4).setCellValue(custo.getValorUnitario().doubleValue());
            row.createCell(5).setCellValue(custo.getValorTotal().doubleValue());
        }


        for (int i = 0; i <= 5; i++) {
            sheetCustos.autoSizeColumn(i);
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}