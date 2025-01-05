package be.unamur.info.infom227.analysis


trait Lattice[L] {
  def top: Option[L]

  def bottom: Option[L]

  def includes(including: L, included: L): Boolean

  def join(value1: L, value2: L): Option[L]

  def meet(value1: L, value2: L): Option[L]
}

case class FiniteSizeLattice[L](edges: Set[(L, L)]) extends Lattice[L] {
  override def top: Option[L] = {
    // The first node that does have a successor (should be unique)
    edges.map(_._2).find(end => !edges.map(_._1).contains(end))
  }

  override def bottom: Option[L] = {
    // The first node that does have a predecessor (should be unique)
    edges.map(_._1).find(start => !edges.map(_._2).contains(start))
  }

  private def successors(value: L): Set[L] = {
    edges.filter(_._1 == value).map(_._2)
  }

  private def predecessors(value: L): Set[L] = {
    edges.filter(_._2 == value).map(_._1)
  }

  override def includes(including: L, included: L): Boolean = {
    val includedSuccessors = successors(included)

    including == included || includedSuccessors.contains(including) || includedSuccessors.exists(includes(including, _))
  }

  override def join(value1: L, value2: L): Option[L] = {
    joinDist(value1, value2).map(_._1)
  }

  private def joinDist(value1: L, value2: L): Option[(L, Int)] = {
    if (value1 == value2) {
      Some((value1, 0))
    } else {
      val successors1 = successors(value1)
      val successors2 = successors(value2)

      // The closest joined value that comes from joining the successors of value1 and the value value2
      val option1 = successors1.foldLeft(None: Option[(L, Int)]) {
        case (None, successor1) => joinDist(successor1, value2)
        case (Some((acc, dist)), end1) => joinDist(end1, value2)
          .map((result1, dist1) => if (dist1 < dist) (result1, dist1) else (acc, dist))
      }

      // The closest joined value that comes from joining the successors of value2 and the value value1
      val option2 = successors2.foldLeft(None: Option[(L, Int)]) {
        case (None, successor2) => joinDist(value1, successor2)
        case (Some((acc, dist)), end2) => joinDist(value1, end2)
          .map((result2, dist2) => if (dist2 < dist) (result2, dist2) else (acc, dist))
      }

      // We take the closest joined value
      (option1, option2) match
        case (Some((result1, dist1)), Some((_, dist2))) if dist1 < dist2 => Some((result1, dist1 + 1))
        case (Some((_, dist1)), Some((result2, dist2))) if dist1 > dist2 => Some((result2, dist2 + 1))
        case (Some((result1, dist1)), Some(_)) => Some((result1, dist1 + 1))
        case (Some((result1, dist1)), None) => Some((result1, dist1 + 1))
        case (None, Some((result2, dist2))) => Some((result2, dist2 + 1))
        case (None, None) => None
    }
  }

  override def meet(value1: L, value2: L): Option[L] = {
    meetDist(value1, value2).map(_._1)
  }

  def meetDist(value1: L, value2: L): Option[(L, Int)] = {
    if (value1 == value2) {
      Some((value1, 0))
    } else {
      val predecessors1 = predecessors(value1)
      val predecessors2 = predecessors(value2)

      // The closest met value that comes from meeting the predecessors of value1 and the value value2
      val option1 = predecessors1.foldLeft(None: Option[(L, Int)]) {
        case (None, predecessor1) => meetDist(predecessor1, value2)
        case (Some((acc, dist)), end1) => meetDist(end1, value2)
          .map((result1, dist1) => if (dist1 < dist) (result1, dist1) else (acc, dist))
      }

      // The closest met value that comes from meeting the predecessors of value2 and the value value1
      val option2 = predecessors2.foldLeft(None: Option[(L, Int)]) {
        case (None, predecessor2) => meetDist(value1, predecessor2)
        case (Some((acc, dist)), end2) => meetDist(value1, end2)
          .map((result2, dist2) => if (dist2 < dist) (result2, dist2) else (acc, dist))
      }

      // We take the closest met value
      (option1, option2) match
        case (Some((result1, dist1)), Some((_, dist2))) if dist1 < dist2 => Some((result1, dist1 + 1))
        case (Some((_, dist1)), Some((result2, dist2))) if dist1 > dist2 => Some((result2, dist2 + 1))
        case (Some((result1, dist1)), Some(_)) => Some((result1, dist1 + 1))
        case (Some((result1, dist1)), None) => Some((result1, dist1 + 1))
        case (None, Some((result2, dist2))) => Some((result2, dist2 + 1))
        case (None, None) => None
    }
  }
}
