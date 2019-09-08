import { ItemPedido } from './ItemPedido';

export class Pedido {
  id: number;
  vendedorId: number;
  vendedorNome: string;
  clienteId: number;
  clienteNome: string;
  dtCadastro: string;
  pedidoEmitido = false;
  itens: ItemPedido[] = [];

}
